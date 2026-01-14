package org.example.apiquanlythuvien.service.sach;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.response.ChiTietSachResponse;
import org.example.apiquanlythuvien.data.response.SachResponse;
import org.example.apiquanlythuvien.mapper.SachMapper;
import org.example.apiquanlythuvien.responsitory.SachRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Slf4j
@Service
public class SachServiceImpl implements SachService {

  private final SachRepository sachRepository;
  private final SachMapper sachMapper;

  @Override
  @Transactional
  public Page<SachResponse> getAllSach(Pageable pageable) {
    return sachRepository.findAll(pageable)
        .map(sachMapper::toResponse);
  }

  @Override
  public ChiTietSachResponse getChiTietSachById(Long sachId) {
    return sachRepository.findById(sachId)
        .map(sachMapper::toChiTietResponse)
        .orElseThrow(() -> new RuntimeException("Sach khong tim thay id: " + sachId));
  }

  @Override
  public Page<SachResponse> getAllSachByTheLoaiId(Long theLoaiId,Pageable pageable) {
    return sachRepository.getAllSachByTheLoaiId(theLoaiId,pageable)
        .map(sachMapper::toResponse);
  }
}
