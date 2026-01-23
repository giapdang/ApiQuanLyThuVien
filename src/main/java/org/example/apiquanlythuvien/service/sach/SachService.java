package org.example.apiquanlythuvien.service.sach;

import org.example.apiquanlythuvien.data.request.CreateSachAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateSachAdminRequest;
import org.example.apiquanlythuvien.data.response.AnhBiaTenSachResponse;
import org.example.apiquanlythuvien.data.response.ChiTietSachResponse;
import org.example.apiquanlythuvien.data.response.SachAdminResponse;
import org.example.apiquanlythuvien.data.response.SachResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SachService {

  Page<SachResponse> getAllSach(Pageable pageable);

  ChiTietSachResponse getChiTietSachById(Long sachId);

  Page<SachResponse> getAllSachByTenTheLoai(String tenTheLoai, Pageable pageable);

  Page<AnhBiaTenSachResponse> getAllSachByTacGiaId(Long tacGiaId, Pageable pageable);

  Page<AnhBiaTenSachResponse> getAllSachByNhaXuatBanId(Long nhaXuatBanId, Pageable pageable);

  Page<SachAdminResponse> getAllSachAdmin(String trangThaiBanSaoSach, Pageable pageable);

  void createSach(CreateSachAdminRequest request);

  void updateSach(UpdateSachAdminRequest request);

  void deleteSach(Long sachId);

  Page<SachResponse> searchSach(String keyword, Pageable pageable);

  Page<SachAdminResponse> searchSachAdmin(String keyword, String trangThaiBanSaoSach, Pageable pageable);
}
