package org.example.apiquanlythuvien.controller;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.service.docgia.DocGiaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/docgia")
@AllArgsConstructor
public class DocGiaController {

  private final DocGiaService docGiaService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllDocGia(@PageableDefault(page = 0, size = 10) Pageable pageable) {
    return ResponseEntity.ok(docGiaService.getAllDocGia(pageable));
  }

  @GetMapping("/chitietdocgia")
  public ResponseEntity<?> getDocGiaById(@RequestParam Long accountId) {
    return ResponseEntity.ok(docGiaService.getDocGiaById(accountId));
  }
}
