package org.example.apiquanlythuvien.service.bansaosach;

import org.example.apiquanlythuvien.data.request.CreateBanSaoSachRequest;
import org.example.apiquanlythuvien.data.request.UpdateBanSaoSachRequest;
import org.example.apiquanlythuvien.data.response.BanSaoSachResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BanSaoSachService {

  Page<BanSaoSachResponse> getAllBanSaoSachBySachId(Long sachId, Pageable pageable);

  void createBanSaoSach(CreateBanSaoSachRequest createBanSaoSachRequest);

  void updateBanSaoSach(UpdateBanSaoSachRequest updateBanSaoSachRequest);
}
