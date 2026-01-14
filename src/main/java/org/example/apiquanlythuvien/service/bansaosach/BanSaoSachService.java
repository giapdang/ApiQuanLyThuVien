package org.example.apiquanlythuvien.service.bansaosach;

import org.example.apiquanlythuvien.data.response.BanSaoSachResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BanSaoSachService {

  Page<BanSaoSachResponse> getAllBanSaoSachBySachId(Long sachId, Pageable pageable);
}
