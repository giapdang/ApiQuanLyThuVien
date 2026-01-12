package org.example.apiquanlythuvien.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.CreateNhaXuatBanAdminRequest;
import org.example.apiquanlythuvien.service.nhaxuatban.NhaXuatBanService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nhaxuatban")
@AllArgsConstructor
public class NhaXuatBanController {

  private final NhaXuatBanService nhaXuatBanService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllNhaXuatBan(Pageable pageable) {
    return ResponseEntity.ok(nhaXuatBanService.getAllNhaXuatBan(pageable));
  }

  @PostMapping("/create")
  public ResponseEntity<?> createNhaXuatBan(@Valid @RequestBody CreateNhaXuatBanAdminRequest request) {
    nhaXuatBanService.createNhaXuatBan(request);
    return ResponseEntity.ok("Tạo nhà xuất bản thành công");
  }
}
