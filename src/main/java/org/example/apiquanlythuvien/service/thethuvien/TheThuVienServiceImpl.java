package org.example.apiquanlythuvien.service.thethuvien;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.response.ThethuVienAdminResponse;
import org.example.apiquanlythuvien.mapper.TheThuVienMapper;
import org.example.apiquanlythuvien.responsitory.TheThuVienRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TheThuVienServiceImpl implements TheThuVienService {

  private  final TheThuVienMapper theThuVienMapper;
  private final TheThuVienRepository theThuVienRepository;

  @Override
  public Page<ThethuVienAdminResponse> getAllTheThuVien(Pageable pageable) {
    return theThuVienRepository.findAllTheThuVienAdmin(pageable);
  }

}
