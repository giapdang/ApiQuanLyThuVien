package org.example.apiquanlythuvien.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.CreateLinhVucAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateLinhVucAdminRequest;
import org.example.apiquanlythuvien.service.linhvuc.LinhVucService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/linhvuc")
@AllArgsConstructor
public class LinhVucController {

    private final LinhVucService linhVucService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllLinhVuc(Pageable pageable) {
        return ResponseEntity.ok(linhVucService.getAllLinhVuc(pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<? > createLinhVuc(@Valid @RequestBody CreateLinhVucAdminRequest request) {
        linhVucService.createLinhVuc(request);
        return new ResponseEntity<>("Tạo lĩnh vực thành công", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLinhVuc(@Valid @RequestBody UpdateLinhVucAdminRequest request) {
        linhVucService.updateLinhVuc(request);
        return ResponseEntity.ok("Cập nhật lĩnh vực thành công");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLinhVuc(@RequestParam Long linhVucId) {
        linhVucService.deleteLinhVuc(linhVucId);
        return ResponseEntity.ok("Xóa lĩnh vực thành công");
    }
}
