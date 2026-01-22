package org.example.apiquanlythuvien.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.UpdateDocGiaAdminRequest;
import org.example.apiquanlythuvien.service.docgia.DocGiaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/docgia")
@AllArgsConstructor
public class DocGiaController {

  private final DocGiaService docGiaService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllDocGia(
      @RequestParam(required = false, defaultValue = "TAT_CA") String trangThaiDocGia,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {
    return ResponseEntity.ok(docGiaService.getAllDocGia(trangThaiDocGia, pageable));
  }

  @GetMapping("/chitietdocgia")
  public ResponseEntity<?> getDocGiaById(@RequestParam Long accountId) {
    return ResponseEntity.ok(docGiaService.getDocGiaById(accountId));
  }

  @GetMapping("/admin/chitietdocgia")
  public ResponseEntity<?> getDocGiaByDocGiaId(@RequestParam Long docGiaId) {
    return ResponseEntity.ok(docGiaService.getDocGiaByDocGiaIdAdmin(docGiaId));
  }

  @PutMapping("/admin/updatedocgia")
  public ResponseEntity<?> updateDocGiaAdmin(@RequestBody UpdateDocGiaAdminRequest updateDocGiaAdminRequest) {
    docGiaService.updateDocGiaAdmin(updateDocGiaAdminRequest);
    return ResponseEntity.ok("Cập nhật độc giả thành công");
  }

  @GetMapping("/admin/count")
  public ResponseEntity<?> countDocGia() {
    long count = docGiaService.countDocGia();
    Map<String,Long> response = new HashMap<>();
    response.put("totalDocGia", count);
    return ResponseEntity.ok(response);
  }
}
