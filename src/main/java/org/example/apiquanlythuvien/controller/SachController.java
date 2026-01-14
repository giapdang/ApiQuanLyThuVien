package org.example.apiquanlythuvien.controller;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.service.sach.SachService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sach")
@AllArgsConstructor
public class SachController {

  private final SachService sachService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllSach(@PageableDefault(page = 0,size = 10, sort = "sachId",
      direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(sachService.getAllSach(pageable));
  }

  @GetMapping("/chitietsach")
  public ResponseEntity<?> getChiTietSachById(@RequestParam Long sachId) {
    return ResponseEntity.ok(sachService.getChiTietSachById(sachId));
  }

  @GetMapping("/theloai")
  public ResponseEntity<?> getAllSachByTheLoaiId(@RequestParam Long theLoaiId,
      @PageableDefault(page = 0,size = 10) Pageable pageable) {
    return ResponseEntity.ok(sachService.getAllSachByTheLoaiId(theLoaiId, pageable));
  }
}
