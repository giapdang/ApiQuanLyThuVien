package org.example.apiquanlythuvien.controller;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.CreateSachAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateSachAdminRequest;
import org.example.apiquanlythuvien.service.sach.SachService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sach")
@AllArgsConstructor
public class SachController {

  private final SachService sachService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllSach(
      @PageableDefault(page = 0, size = 10, sort = "sachId", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(sachService.getAllSach(pageable));
  }

  @GetMapping("/chitietsach")
  public ResponseEntity<?> getChiTietSachById(@RequestParam Long sachId) {
    return ResponseEntity.ok(sachService.getChiTietSachById(sachId));
  }

  @GetMapping("/admin/all")
  public ResponseEntity<?> getAllSachAdmin(
      @PageableDefault(page = 0, size = 10, sort = "sachId", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(sachService.getAllSachAdmin(pageable));
  }

  @GetMapping("/theloai")
  public ResponseEntity<?> getAllSachByTheLoaiId(@RequestParam Long theLoaiId,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {
    return ResponseEntity.ok(sachService.getAllSachByTheLoaiId(theLoaiId, pageable));
  }

  @PostMapping("/admin/create")
  public ResponseEntity<?> createSach(@Valid @RequestBody CreateSachAdminRequest request) {
    sachService.createSach(request);
    return new ResponseEntity<>("Thêm sách thành công", HttpStatus.CREATED);
  }

  @PutMapping("/admin/update")
  public ResponseEntity<?> updateSach(@Valid @RequestBody UpdateSachAdminRequest request) {
    sachService.updateSach(request);
    return ResponseEntity.ok("Cập nhật sách thành công");
  }

  @DeleteMapping("/admin/delete")
  public ResponseEntity<?> deleteSach(@RequestParam Long sachId) {
    sachService.deleteSach(sachId);
    return ResponseEntity.ok("Xóa sách thành công");
  }
}
