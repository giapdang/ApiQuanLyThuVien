package org.example.apiquanlythuvien.controller;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.CreateBanSaoSachRequest;
import org.example.apiquanlythuvien.data.request.UpdateBanSaoSachRequest;
import org.example.apiquanlythuvien.service.bansaosach.BanSaoSachService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bansaosach")
@AllArgsConstructor
public class BanSaoSachController {

  private final BanSaoSachService banSaoSachService;

  @GetMapping("/danhsach")
  public ResponseEntity<?> getAllBanSaoSachBySachId(@RequestParam Long sachId, Pageable pageable) {
    return ResponseEntity.ok(banSaoSachService.getAllBanSaoSachBySachId(sachId, pageable));
  }

  @PostMapping("/create")
  public ResponseEntity<?> createBanSaoSach(@Valid @RequestBody CreateBanSaoSachRequest request) {
    banSaoSachService.createBanSaoSach(request);
    return ResponseEntity.ok("Tạo bản sao sách thành công");
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateBanSaoSach(@Valid @RequestBody UpdateBanSaoSachRequest request) {
    banSaoSachService.updateBanSaoSach(request);
    return ResponseEntity.ok("Cập nhật bản sao sách thành công");
  }

  @GetMapping("/count")
  public ResponseEntity<?> countBanSaoSach() {
    long count = banSaoSachService.countBanSaoSach();
    Map<String,Long> response = new HashMap<>();
    response.put("totalBanSaoSach", count);
    return ResponseEntity.ok((response));
  }
}
