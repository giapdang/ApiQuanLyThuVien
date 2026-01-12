package org.example.apiquanlythuvien.service.tacgia;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.TacGia;
import org.example.apiquanlythuvien.data.request.CreateTacGiaAdminRequest;
import org.example.apiquanlythuvien.data.response.TacGiaAdminResponse;
import org.example.apiquanlythuvien.mapper.TacGiaMapper;
import org.example.apiquanlythuvien.responsitory.TacGiaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class TacGiaServiceImpl implements TacGiaService {

  private final TacGiaRepository tacGiaRepository;
  private final TacGiaMapper tacGiaMapper;

  @Override
  @Transactional
  public Page<TacGiaAdminResponse> getAllTacGia(Pageable pageable) {
    return tacGiaRepository.findAll(pageable)
        .map(tacGiaMapper::toTacGiaResponseAdminMapper);

  }

  @Override
  public void createTacGia(CreateTacGiaAdminRequest request) {
    TacGia tacGia = tacGiaMapper.toEntityTacGiaMapper(request);
    tacGiaRepository.save(tacGia);
  }
}
