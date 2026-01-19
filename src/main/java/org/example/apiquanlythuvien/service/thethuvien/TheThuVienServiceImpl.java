package org.example.apiquanlythuvien.service.thethuvien;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.TheThuVien;
import org.example.apiquanlythuvien.data.request.UpdateTheThuVienRequest;
import org.example.apiquanlythuvien.data.response.ThethuVienAdminResponse;
import org.example.apiquanlythuvien.exception.NotFoundException;
import org.example.apiquanlythuvien.mapper.TheThuVienMapper;
import org.example.apiquanlythuvien.responsitory.TheThuVienRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TheThuVienServiceImpl implements TheThuVienService {

  private final TheThuVienMapper theThuVienMapper;
  private final TheThuVienRepository theThuVienRepository;

  @Override
  public Page<ThethuVienAdminResponse> getAllTheThuVien(String trangThai, Pageable pageable) {
    return theThuVienRepository.findAllTheThuVienAdmin(trangThai, pageable);
  }

  @Override
  public void updateTheThuVien(UpdateTheThuVienRequest request) {
    TheThuVien theThuVien = theThuVienRepository.findById(request.getTheThuVienId())
        .orElseThrow(() -> new NotFoundException("Không tìm thấy thẻ thư viện với ID: " + request.getTheThuVienId()));
    theThuVienMapper.updateTheThuVienMapper(request, theThuVien);
    theThuVienRepository.save(theThuVien);
  }

}
