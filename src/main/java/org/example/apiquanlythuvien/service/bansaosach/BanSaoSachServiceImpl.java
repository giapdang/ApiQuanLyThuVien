package org.example.apiquanlythuvien.service.bansaosach;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.BanSaoSach;
import org.example.apiquanlythuvien.data.entity.Sach;
import org.example.apiquanlythuvien.data.request.CreateBanSaoSachRequest;
import org.example.apiquanlythuvien.data.request.UpdateBanSaoSachRequest;
import org.example.apiquanlythuvien.data.response.BanSaoSachResponse;
import org.example.apiquanlythuvien.mapper.BanSaoSachMapper;
import org.example.apiquanlythuvien.responsitory.BanSaoSachRepository;
import org.example.apiquanlythuvien.responsitory.SachRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BanSaoSachServiceImpl implements BanSaoSachService {

  private final BanSaoSachRepository banSaoSachRepository;
  private final BanSaoSachMapper banSaoSachMapper;
  private final SachRepository sachRepository;


  @Override
  public Page<BanSaoSachResponse> getAllBanSaoSachBySachId(Long sachId, Pageable pageable) {
    return banSaoSachRepository.findAllBanSaoSachBySachId(sachId, pageable);
  }

  @Override
  public void createBanSaoSach(CreateBanSaoSachRequest createBanSaoSachRequest) {
    Sach sach = sachRepository.findById(createBanSaoSachRequest.getSachId())
        .orElseThrow(() -> new IllegalArgumentException(
            "Sach khong tim thay voi id: " + createBanSaoSachRequest.getSachId()));
    BanSaoSach banSaoSach = banSaoSachMapper.toBanSaoSachMapper(createBanSaoSachRequest, sach);
    banSaoSachRepository.save(banSaoSach);

  }

  @Override
  public void updateBanSaoSach(UpdateBanSaoSachRequest updateBanSaoSachRequest) {
    BanSaoSach banSaoSach = banSaoSachRepository.findById(updateBanSaoSachRequest.getBanSaoSachId())
        .orElseThrow(() -> new IllegalArgumentException(
            "Ban sao sach khong tim thay voi id: " + updateBanSaoSachRequest.getBanSaoSachId()));
    banSaoSachMapper.updateBanSaoSachFromRequest(updateBanSaoSachRequest, banSaoSach);
    banSaoSachRepository.save(banSaoSach);
  }

  @Override
  public long countBanSaoSach() {
    return banSaoSachRepository.count();
  }
}
