package org.example.apiquanlythuvien.service.bansaosach;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.response.BanSaoSachResponse;
import org.example.apiquanlythuvien.mapper.BanSaoSachMapper;
import org.example.apiquanlythuvien.responsitory.BanSaoSachRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BanSaoSachServiceImpl implements BanSaoSachService {

  private final BanSaoSachRepository banSaoSachRepository;
  private final BanSaoSachMapper banSaoSachMapper;


  @Override
  public Page<BanSaoSachResponse> getAllBanSaoSachBySachId(Long sachId, Pageable pageable) {
    return banSaoSachRepository.findAllBanSaoSachBySachId(sachId, pageable);
  }
}
