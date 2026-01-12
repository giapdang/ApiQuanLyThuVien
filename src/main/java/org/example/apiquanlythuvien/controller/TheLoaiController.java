package org.example.apiquanlythuvien.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.CreateTheLoaiAdminRequest;
import org.example.apiquanlythuvien.service.theloai.TheLoaiService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/theloai")
@AllArgsConstructor
public class TheLoaiController {

  private final TheLoaiService theLoaiService;

  @GetMapping("/all")
  public ResponseEntity<?> getAllTheLoai(Pageable pageable) {
    return ResponseEntity.ok(theLoaiService.getAllTheLoai(pageable));
  }

  @PostMapping("/create")
  public ResponseEntity<?> createTheLoai(@Valid @RequestBody CreateTheLoaiAdminRequest request) {
    theLoaiService.createTheLoai(request);
    return ResponseEntity.ok("Tạo thể loại thành công");
  }
}
