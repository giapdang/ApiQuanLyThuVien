package org.example.apiquanlythuvien.controller;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.entity.PhieuMuon;
import org.example.apiquanlythuvien.data.request.CreatePhieuMuonRequest;
import org.example.apiquanlythuvien.data.response.ChiTietMuonTraResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/chitietmuontra")
  public ResponseEntity<List<ChiTietMuonTraResponse>> getChiTietMuonTra(
      @RequestParam Long phieuMuonId) {
    return ResponseEntity.ok(phieuMuonService.getChiTietMuonTraByPhieuMuonId(phieuMuonId));
  }

}
