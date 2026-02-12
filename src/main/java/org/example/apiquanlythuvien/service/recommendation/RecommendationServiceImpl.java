package org.example.apiquanlythuvien.service.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.apiquanlythuvien.data.entity.Sach;
import org.example.apiquanlythuvien.data.entity.SachTacGia;
import org.example.apiquanlythuvien.data.response.BookRecommendationResponse;
import org.example.apiquanlythuvien.data.response.TacGiaResponse;
import org.example.apiquanlythuvien.mapper.SachMapper;
import org.example.apiquanlythuvien.responsitory.ChiTietMuonTraRepository;
import org.example.apiquanlythuvien.responsitory.SachRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

  private final ChiTietMuonTraRepository chiTietMuonTraRepository;
  private final SachRepository sachRepository;
  private final SachMapper sachMapper;

  private static final double GENRE_WEIGHT = 0.40;
  private static final double FIELD_WEIGHT = 0.35;
  private static final double AUTHOR_WEIGHT = 0.25;
  private static final double POPULARITY_BONUS_MAX = 0.10;

  @Override
  public List<BookRecommendationResponse> getRecommendation(Long docGiaId, int limit) {
    Map<String, Double> genrePreferences = calculateGenrePreferences(docGiaId);
    Map<String, Double> fieldPreferences = calculateFieldPreferences(docGiaId);
    Set<String> favoriteAuthors = getFavoriteAuthors(docGiaId);

    boolean hasHistory = !genrePreferences.isEmpty() || !fieldPreferences.isEmpty()
        || !favoriteAuthors.isEmpty();

    if (!hasHistory) {
      return getPopularBooksAsRecommendations(limit);
    }

    List<Long> borrowedBookIds = chiTietMuonTraRepository.findBorrowedBookIdsByDocGiaId(docGiaId);
    List<Sach> candidateBooks;

    if (borrowedBookIds.isEmpty()) {
      candidateBooks = sachRepository.findAllBooks();
    } else {
      candidateBooks = sachRepository.findBooksNotBorrowedByUser(borrowedBookIds);
    }

    if (candidateBooks.isEmpty()) {
      return Collections.emptyList();
    }

    Map<Long, Long> popularityMap = getPopularityMap();
    long maxPopularity = popularityMap.values().stream()
        .max(Long::compareTo)
        .orElse(1L);

    List<ScoredBook> scoredBooks = new ArrayList<>();
    for (Sach book : candidateBooks) {
      double score = scoreBook(book, genrePreferences, fieldPreferences,
          favoriteAuthors, popularityMap, maxPopularity);
      scoredBooks.add(new ScoredBook(book, score));
    }

    scoredBooks.sort(Comparator.comparing(ScoredBook::score).reversed());

    return scoredBooks.stream()
        .limit(limit)
        .map(this::toRecommendationResponse)
        .collect(Collectors.toList());
  }

  private List<BookRecommendationResponse> getPopularBooksAsRecommendations(int limit) {
    Map<Long, Long> popularityMap = getPopularityMap();
    long maxPopularity = popularityMap.values().stream()
        .max(Long::compareTo)
        .orElse(1L);

    List<Sach> allBooks = sachRepository.findAllBooks();

    if (allBooks.isEmpty()) {
      return Collections.emptyList();
    }
    List<ScoredBook> scoredBooks = new ArrayList<>();
    for (Sach book : allBooks) {
      long borrowCount = popularityMap.getOrDefault(book.getSachId(), 0L);
      double score = (maxPopularity > 0)
          ? borrowCount / (double) maxPopularity
          : 0.0;
      scoredBooks.add(new ScoredBook(book, score));
    }

    scoredBooks.sort(Comparator.comparing(ScoredBook::score).reversed());

    return scoredBooks.stream()
        .limit(limit)
        .map(this::toRecommendationResponse)
        .collect(Collectors.toList());
  }

  private Map<String, Double> calculateGenrePreferences(Long docGiaId) {
    List<Object[]> results = chiTietMuonTraRepository.getGenreFrequencyByUser(docGiaId);

    if (results.isEmpty()) {
      return Collections.emptyMap();
    }

    long total = results.stream()
        .mapToLong(r -> (Long) r[1])
        .sum();

    Map<String, Double> preferences = new HashMap<>();
    for (Object[] row : results) {
      String genre = (String) row[0];
      Long count = (Long) row[1];
      preferences.put(genre, count.doubleValue() / total);
    }

    return preferences;
  }

  private Map<String, Double> calculateFieldPreferences(Long docGiaId) {
    List<Object[]> results = chiTietMuonTraRepository.getFieldFrequencyByUser(docGiaId);

    if (results.isEmpty()) {
      return Collections.emptyMap();
    }
    long total = results.stream()
        .mapToLong(r -> (Long) r[1])
        .sum();

    Map<String, Double> preferences = new HashMap<>();
    for (Object[] row : results) {
      String field = (String) row[0];
      Long count = (Long) row[1];
      preferences.put(field, count.doubleValue() / total);
    }

    return preferences;
  }

  private Set<String> getFavoriteAuthors(Long docGiaId) {
    List<String> authors = chiTietMuonTraRepository.getFavoriteAuthorsByUser(docGiaId);
    return new HashSet<>(authors);
  }

  private Map<Long, Long> getPopularityMap() {
    List<Object[]> results = sachRepository.getBookPopularityCounts();

    Map<Long, Long> popularityMap = new HashMap<>();
    for (Object[] row : results) {
      Long sachId = (Long) row[0];
      Long count = (Long) row[1];
      popularityMap.put(sachId, count);
    }

    return popularityMap;
  }

  private double scoreBook(Sach book,
      Map<String, Double> genrePreferences,
      Map<String, Double> fieldPreferences,
      Set<String> favoriteAuthors,
      Map<Long, Long> popularityMap,
      long maxPopularity) {

    String bookGenre = book.getTheLoai() != null ? book.getTheLoai().getTenTheLoai() : "";
    double genreScore = genrePreferences.getOrDefault(bookGenre, 0.0);

    String bookField = book.getLinhVuc() != null ? book.getLinhVuc().getTenLinhVuc() : "";
    double fieldScore = fieldPreferences.getOrDefault(bookField, 0.0);

    double authorScore = hasCommonAuthor(book, favoriteAuthors) ? 1.0 : 0.0;

    long borrowCount = popularityMap.getOrDefault(book.getSachId(), 0L);
    double popularityBonus = (maxPopularity > 0)
        ? (borrowCount / (double) maxPopularity) * POPULARITY_BONUS_MAX
        : 0.0;

    return (GENRE_WEIGHT * genreScore) +
        (FIELD_WEIGHT * fieldScore) +
        (AUTHOR_WEIGHT * authorScore) +
        popularityBonus;
  }

  private boolean hasCommonAuthor(Sach book, Set<String> favoriteAuthors) {
    if (book.getSachTacGia() == null || book.getSachTacGia().isEmpty()) {
      return false;
    }

    return book.getSachTacGia().stream()
        .map(SachTacGia::getTacGia)
        .filter(tacGia -> tacGia != null)
        .map(tacGia -> tacGia.getTenTacGia())
        .anyMatch(favoriteAuthors::contains);
  }

  private BookRecommendationResponse toRecommendationResponse(ScoredBook scoredBook) {
    Sach book = scoredBook.sach();

    List<TacGiaResponse> authors = new ArrayList<>();
    if (book.getSachTacGia() != null) {
      authors = book.getSachTacGia().stream()
          .filter(stg -> stg.getTacGia() != null)
          .map(stg -> {
            TacGiaResponse tacGiaResponse = new TacGiaResponse();
            tacGiaResponse.setTacGiaId(stg.getTacGia().getTacGiaId());
            tacGiaResponse.setTenTacGia(stg.getTacGia().getTenTacGia());
            return tacGiaResponse;
          })
          .collect(Collectors.toList());
    }

    return new BookRecommendationResponse(
        book.getSachId(),
        book.getTenSach(),
        authors,
        book.getAnhBia(),
        Math.round(scoredBook.score() * 100.0) / 100.0
    );
  }
    private record ScoredBook(Sach sach, Double score) {
      @Override
      public Sach sach() {
        return sach;
      }

      @Override
      public Double score() {
        return score;
      }
    }
}
