package org.example.apiquanlythuvien.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.UpdateChiTietStatusRequest;
import org.example.apiquanlythuvien.data.request.UpdatePhieuMuonStatusRequest;
import org.example.apiquanlythuvien.data.entity.PhieuMuon;
import org.example.apiquanlythuvien.data.request.CreatePhieuMuonRequest;
import org.example.apiquanlythuvien.data.response.ChiTietMuonTraResponse;
import org.example.apiquanlythuvien.data.response.DocGiaFromChiTietResponse;
import org.example.apiquanlythuvien.data.response.PhieuMuonResponse;
import org.example.apiquanlythuvien.service.phieumuon.PhieuMuonService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phieumuon")
@AllArgsConstructor
public class PhieuMuonController {

  private final PhieuMuonService phieuMuonService;

  @PostMapping("/create")
  public ResponseEntity<?> createPhieuMuon(@RequestBody CreatePhieuMuonRequest request) {
    phieuMuonService.createPhieuMuon(request);
    return ResponseEntity.ok("tạo phiếu mượn thành công");
  }

  @GetMapping("/load")
  public ResponseEntity<?> getPhieuMuon(
      @RequestParam(required = false, defaultValue = "TAT_CA") String trangThai,
      @PageableDefault(sort = "ngayMuon", direction = Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(phieuMuonService.getPhieuMuonByTrangThai(trangThai, pageable));
  }

  @GetMapping("/admin/load")
  public ResponseEntity<?> getAllPhieuMuon(
      @RequestParam(required = false, defaultValue = "TAT_CA") String trangThai,
      @PageableDefault(sort = "ngayMuon", direction = Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(phieuMuonService.getAllPhieuMuon(trangThai, pageable));
  }

  @GetMapping("/admin/search")
  public ResponseEntity<?> searchPhieuMuonAdmin(
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false, defaultValue = "TAT_CA") String trangThai,
      @PageableDefault(sort = "ngayMuon", direction = Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(phieuMuonService.searchPhieuMuonAdmin(keyword, trangThai, pageable));
  }

  @GetMapping("/chitietmuontra")
  public ResponseEntity<List<ChiTietMuonTraResponse>> getChiTietMuonTra(
      @RequestParam Long phieuMuonId) {
    return ResponseEntity.ok(phieuMuonService.getChiTietMuonTraByPhieuMuonId(phieuMuonId));
  }

  @PutMapping("/admin/update-phieumuon-status")
  public ResponseEntity<?> updatePhieuMuonStatus(
      @RequestParam Long phieuMuonId,
      @RequestBody UpdatePhieuMuonStatusRequest request) {
    phieuMuonService.updatePhieuMuonStatus(phieuMuonId, request.getTrangThai());
    return ResponseEntity.ok("Cập nhật trạng thái phiếu mượn thành công");
  }

  @PutMapping("/admin/update-chitiet-status")
  public ResponseEntity<?> updateChiTietStatus(
      @RequestParam Long chiTietPhieuMuonId,
      @RequestBody UpdateChiTietStatusRequest request) {
    phieuMuonService.updateChiTietStatus(chiTietPhieuMuonId, request.getTinhTrang());
    return ResponseEntity.ok("Cập nhật tình trạng chi tiết mượn trả thành công");
  }

  @GetMapping("/admin/count")
  public ResponseEntity<?> countPhieuMuonDangMuon() {
    long count = phieuMuonService.countPhieuMuonDangMuon();
    Map<String,Long> response = new HashMap<>();
    response.put("totalPhieuMuon", count);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/admin/count/qua-han")
  public ResponseEntity<?> countPhieuMuonQuaHan() {
    long count = phieuMuonService.countPhieuMuonQuaHan();
    Map<String, Long> response = new HashMap<>();
    response.put("totalPhieuMuonQuaHan", count);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/admin/chitietphieumuon/doc-gia")
  public ResponseEntity<?> getDocGiaByChiTietMuonTra(
      @RequestParam Long chiTietId) {
    return ResponseEntity.ok(phieuMuonService.getDocGiaByChiTietMuonTraId(chiTietId));
  }

}
