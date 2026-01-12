package org.example.apiquanlythuvien.service.nhaxuatban;

import org.example.apiquanlythuvien.data.request.CreateNhaXuatBanAdminRequest;
import org.example.apiquanlythuvien.data.response.NhaXuatBanAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NhaXuatBanService {

  Page<NhaXuatBanAdminResponse> getAllNhaXuatBan(Pageable pageable);

  void createNhaXuatBan(CreateNhaXuatBanAdminRequest request);
}
