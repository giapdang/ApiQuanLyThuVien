package org.example.apiquanlythuvien.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.CreateNhaXuatBanAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateNhaXuatBanAdminRequest;
import org.example.apiquanlythuvien.service.nhaxuatban.NhaXuatBanService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PutMapping("/update")
  public ResponseEntity<?> updateNhaXuatBan(@Valid @RequestBody UpdateNhaXuatBanAdminRequest request) {
    nhaXuatBanService.updateNhaXuatBan(request);
    return ResponseEntity.ok("Cập nhật nhà xuất bản thành công");
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteNhaXuatBan(@RequestParam Long nhaXuatBanId) {
    nhaXuatBanService.deleteNhaXuatBan(nhaXuatBanId);
    return ResponseEntity.ok("Xóa nhà xuất bản thành công");
  }
}
