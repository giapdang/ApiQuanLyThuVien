package org.example.apiquanlythuvien.controller;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.UpdateTheThuVienRequest;
import org.example.apiquanlythuvien.service.theloai.TheLoaiService;
import org.example.apiquanlythuvien.service.thethuvien.TheThuVienService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PutMapping("/update")
  public ResponseEntity<?> updateTheThuVien(@RequestBody UpdateTheThuVienRequest request) {
    theThuVienService.updateTheThuVien(request);
    return ResponseEntity.ok("Cập nhật thẻ thư viện thành công");
  }
}
