package org.example.apiquanlythuvien.controller;

import java.util.HashMap;
import java.util.Map;
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

  @GetMapping("/search")
  public ResponseEntity<?> searchSach(
      @RequestParam String keyword,
      @PageableDefault(page = 0, size = 10, sort = "sachId", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(sachService.searchSach(keyword, pageable));
  }

  @GetMapping("/admin/search")
  public ResponseEntity<?> searchSachAdmin(
      @RequestParam String keyword,
      @RequestParam(required = false) String trangThaiBanSaoSach,
      @PageableDefault(page = 0, size = 10, sort = "sachId", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(sachService.searchSachAdmin(keyword, trangThaiBanSaoSach, pageable));
  }

  @GetMapping("/chitietsach")
  public ResponseEntity<?> getChiTietSachById(@RequestParam Long sachId) {
    return ResponseEntity.ok(sachService.getChiTietSachById(sachId));
  }

  @GetMapping("/admin/all")
  public ResponseEntity<?> getAllSachAdmin(
      @RequestParam(required = false) String trangThaiBanSaoSach,
      @PageableDefault(page = 0, size = 10, sort = "sachId", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(sachService.getAllSachAdmin(trangThaiBanSaoSach, pageable));
  }

  @GetMapping("/theloai")
  public ResponseEntity<?> getAllSachByTheLoaiId(@RequestParam String tenTheLoai,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {
    return ResponseEntity.ok(sachService.getAllSachByTenTheLoai(tenTheLoai, pageable));
  }

  @GetMapping("/tacgia")
  public ResponseEntity<?> getAllSachByTacGiaId(@RequestParam Long tacGiaId,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {
    return ResponseEntity.ok(sachService.getAllSachByTacGiaId(tacGiaId, pageable));
  }

  @GetMapping("/nhaxuatban")
  public ResponseEntity<?> getAllSachByNhaXuatBanId(@RequestParam Long nhaXuatBanId,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {
    return ResponseEntity.ok(sachService.getAllSachByNhaXuatBanId(nhaXuatBanId, pageable));
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

  @GetMapping("/admin/count")
  public ResponseEntity<?> countSach() {
    long count = sachService.countSach();
    Map<String, Object> response = new HashMap<>();
    response.put("totalSach", count);
    return ResponseEntity.ok(response);
  }
}
