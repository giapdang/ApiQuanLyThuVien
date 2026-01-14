package org.example.apiquanlythuvien.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.CreateTheLoaiAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateTheLoaiAdminRequest;
import org.example.apiquanlythuvien.service.theloai.TheLoaiService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PutMapping("/update")
  public ResponseEntity<?> updateTheLoai(@Valid @RequestBody UpdateTheLoaiAdminRequest request) {
    theLoaiService.updateTheLoai(request);
    return ResponseEntity.ok("Cập nhật thể loại thành công");
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteTheLoai(@RequestParam Long theLoaiId) {
    theLoaiService.deleteTheLoai(theLoaiId);
    return ResponseEntity.ok("Xóa thể loại thành công");
  }
}
