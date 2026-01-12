package org.example.apiquanlythuvien.controller;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.service.theloai.TheLoaiService;
import org.example.apiquanlythuvien.service.thethuvien.TheThuVienService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thethuvien")
@AllArgsConstructor
public class TheThuVienController {

  private final TheThuVienService theThuVienService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllTheThuVien(Pageable pageable) {
    return ResponseEntity.ok(theThuVienService.getAllTheThuVien(pageable));
  }
}
