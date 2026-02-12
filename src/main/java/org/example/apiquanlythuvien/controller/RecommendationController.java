package org.example.apiquanlythuvien.controller;

import lombok.RequiredArgsConstructor;
import org.example.apiquanlythuvien.data.entity.Account;
import org.example.apiquanlythuvien.data.entity.DocGia;
import org.example.apiquanlythuvien.data.response.BookRecommendationResponse;
import org.example.apiquanlythuvien.exception.BadRequestException;
import org.example.apiquanlythuvien.exception.NotFoundException;
import org.example.apiquanlythuvien.responsitory.AccountRepository;
import org.example.apiquanlythuvien.service.recommendation.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

  private final RecommendationService recommendationService;
  private final AccountRepository accountRepository;

  @GetMapping("")
  public ResponseEntity<List<BookRecommendationResponse>> getRecommendation(
      @RequestParam(defaultValue = "10") int limit) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new BadRequestException("Chua dang nhap");
    }

    String username = authentication.getName();

    Account account = accountRepository.findByUsername(username)
        .orElseThrow(() -> new NotFoundException("Khong tim thay tai khoan"));

    DocGia docGia = account.getDocGia();
    if (docGia == null) {
      throw new NotFoundException("Tai khoan chua co thong tin doc gia");
    }

    List<BookRecommendationResponse> recommendations =
        recommendationService.getRecommendation(docGia.getDocGiaId(), limit);

    return ResponseEntity.ok(recommendations);
  }
}
