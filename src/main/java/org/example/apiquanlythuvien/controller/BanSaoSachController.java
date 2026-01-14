package org.example.apiquanlythuvien.controller;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.service.bansaosach.BanSaoSachService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bansaosach")
@AllArgsConstructor
public class BanSaoSachController {

  private final BanSaoSachService banSaoSachService;

  @GetMapping("/danhsach")
  public ResponseEntity<?> getAllBanSaoSachBySachId(@RequestParam Long sachId, Pageable pageable) {
    return ResponseEntity.ok(banSaoSachService.getAllBanSaoSachBySachId(sachId, pageable));
  }
}
