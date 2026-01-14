package org.example.apiquanlythuvien.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.CreateTacGiaAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateTacGiaAdminRequest;
import org.example.apiquanlythuvien.service.tacgia.TacGiaService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tacgia")
@AllArgsConstructor
public class TacGiaController {

  private final TacGiaService tacGiaService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllTacGia(Pageable pageable) {
    return ResponseEntity.ok(tacGiaService.getAllTacGia(pageable));
  }

  @PostMapping("/create")
  public ResponseEntity<?> createTacGia(@Valid @RequestBody CreateTacGiaAdminRequest request) {
    tacGiaService.createTacGia(request);
    return ResponseEntity.ok("Tạo tác giả thành công");
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateTacGia(@Valid @RequestBody UpdateTacGiaAdminRequest request) {
    tacGiaService.updateTacGia(request);
    return ResponseEntity.ok("Cập nhật tác giả thành công");
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteTacGia(@RequestParam Long tacGiaId) {
    tacGiaService.deleteTacGia(tacGiaId);
    return ResponseEntity.ok("Xóa tác giả thành công");
  }
}
