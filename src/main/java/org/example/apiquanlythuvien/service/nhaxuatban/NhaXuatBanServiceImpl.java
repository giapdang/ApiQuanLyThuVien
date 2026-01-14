package org.example.apiquanlythuvien.service.nhaxuatban;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.NhaXuatBan;
import org.example.apiquanlythuvien.data.request.CreateNhaXuatBanAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateNhaXuatBanAdminRequest;
import org.example.apiquanlythuvien.data.response.NhaXuatBanAdminResponse;
import org.example.apiquanlythuvien.mapper.NhaXuatBanMapper;
import org.example.apiquanlythuvien.responsitory.NhaXuatBanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class NhaXuatBanServiceImpl implements  NhaXuatBanService {

  private final NhaXuatBanRepository nhaXuatBanRepository;
  private final NhaXuatBanMapper nhaXuatBanMapper;

  @Override
  public Page<NhaXuatBanAdminResponse> getAllNhaXuatBan(Pageable pageable) {
    return nhaXuatBanRepository.findAll(pageable)
        .map(nhaXuatBanMapper::toNhaXuatBanAdminResponseMapper);
  }

  @Override
  public void createNhaXuatBan(CreateNhaXuatBanAdminRequest request) {
    NhaXuatBan nhaXuatBan = nhaXuatBanMapper.toNhaXuatBanEntityMapper(request);
    nhaXuatBanRepository.save(nhaXuatBan);
  }

  @Override
  public void updateNhaXuatBan(UpdateNhaXuatBanAdminRequest request) {
    NhaXuatBan nhaXuatBan = nhaXuatBanRepository.findById(request.getNhaXuatBanId())
        .orElseThrow(() -> new RuntimeException("Nhà xuất bản không tồn tại với ID: " + request.getNhaXuatBanId()));
    nhaXuatBanMapper.updateNhaXuatBanFromRequest(request, nhaXuatBan);
    nhaXuatBanRepository.save(nhaXuatBan);
  }

  @Override
  public void deleteNhaXuatBan(Long nhaXuatBanId) {
    nhaXuatBanRepository.deleteById(nhaXuatBanId);
  }
}
