package org.example.apiquanlythuvien.service.recommendation;

import java.util.List;
import org.example.apiquanlythuvien.data.response.BookRecommendationResponse;
import org.example.apiquanlythuvien.data.response.SachResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecommendationService {
  List<BookRecommendationResponse> getRecommendation(Long docGiaId, int limit);
}
