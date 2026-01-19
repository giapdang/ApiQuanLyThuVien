package org.example.apiquanlythuvien.service.thethuvien;

import org.example.apiquanlythuvien.data.request.UpdateTheThuVienRequest;
import org.example.apiquanlythuvien.data.response.ThethuVienAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TheThuVienService {

  Page<ThethuVienAdminResponse> getAllTheThuVien(Pageable pageable);

  void updateTheThuVien(UpdateTheThuVienRequest request);
}
