package org.example.apiquanlythuvien.service.sach;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.request.CreateSachAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateSachAdminRequest;
import org.example.apiquanlythuvien.data.response.ChiTietSachResponse;
import org.example.apiquanlythuvien.data.response.SachAdminResponse;
import org.example.apiquanlythuvien.data.response.SachResponse;
import org.example.apiquanlythuvien.mapper.SachMapper;
import org.example.apiquanlythuvien.responsitory.*;
import org.example.apiquanlythuvien.data.entity.*;
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
  private final NhaXuatBanRepository nhaXuatBanRepository;
  private final LinhVucRepository linhVucRepository;
  private final TheLoaiRepository theLoaiRepository;
  private final TacGiaRepository tacGiaRepository;

  @Override
  @Transactional
  public Page<SachResponse> getAllSach(Pageable pageable) {
    return sachRepository.findAll(pageable)
        .map(sachMapper::toResponse);
  }

  @Override
  @Transactional
  public ChiTietSachResponse getChiTietSachById(Long sachId) {
    return sachRepository.findById(sachId)
        .map(sachMapper::toChiTietResponse)
        .orElseThrow(() -> new RuntimeException("Sach khong tim thay id: " + sachId));
  }

  @Override
  @Transactional
  public Page<SachResponse> getAllSachByTenTheLoai(String tenTheLoai, Pageable pageable) {
    return sachRepository.getAllSachByTenTheLoai(tenTheLoai, pageable)
        .map(sachMapper::toResponse);
  }

  @Override
  @Transactional
  public Page<SachAdminResponse> getAllSachAdmin(Pageable pageable) {
    return sachRepository.findAll(pageable)
        .map(sachMapper::toAdminResponse);
  }

  @Override
  @Transactional
  public void createSach(CreateSachAdminRequest request) {
    Sach sach = sachMapper.toSachMapper(request);
    mapRelationships(sach, request.getNhaXuatBanId(), request.getLinhVucId(), request.getTheLoaiId(),
        request.getTacGiaIds());
    sachRepository.save(sach);
  }

  @Override
  @Transactional
  public void updateSach(UpdateSachAdminRequest request) {
    Sach sach = sachRepository.findById(request.getSachId())
        .orElseThrow(() -> new RuntimeException("Sach khong tim thay id: " + request.getSachId()));

    // Update basic fields
    sachMapper.updateSachFromRequest(request, sach);

    // Update relationships
    mapRelationships(sach, request.getNhaXuatBanId(), request.getLinhVucId(), request.getTheLoaiId(),
        request.getTacGiaIds());

    sachRepository.save(sach);
  }

  @Override
  @Transactional
  public Page<SachResponse> searchSach(String keyword, Pageable pageable) {
    return sachRepository.searchByKeyword(keyword, pageable)
        .map(sachMapper::toResponse);
  }

  @Override
  @Transactional
  public void deleteSach(Long sachId) {
    if (!sachRepository.existsById(sachId)) {
      throw new RuntimeException("Sach khong tim thay id: " + sachId);
    }
    sachRepository.deleteById(sachId);
  }

  private void mapRelationships(Sach sach, Long nxbId, Long lvId, Long tlId, java.util.List<Long> tgIds) {
    NhaXuatBan nxb = nhaXuatBanRepository.findById(nxbId)
        .orElseThrow(() -> new RuntimeException("Nha xuat ban khong tim thay id: " + nxbId));
    LinhVuc lv = linhVucRepository.findById(lvId)
        .orElseThrow(() -> new RuntimeException("Linh vuc khong tim thay id: " + lvId));
    TheLoai tl = theLoaiRepository.findById(tlId)
        .orElseThrow(() -> new RuntimeException("The loai khong tim thay id: " + tlId));

    sach.setNhaXuatBan(nxb);
    sach.setLinhVuc(lv);
    sach.setTheLoai(tl);

    // Handle SachTacGia
    if (sach.getSachTacGia() != null) {
      sach.getSachTacGia().clear();
    } else {
      sach.setSachTacGia(new java.util.ArrayList<>());
    }

    for (Long tgId : tgIds) {
      TacGia tg = tacGiaRepository.findById(tgId)
          .orElseThrow(() -> new RuntimeException("Tac gia khong tim thay id: " + tgId));
      SachTacGia stg = new SachTacGia();
      stg.setSach(sach);
      stg.setTacGia(tg);
      sach.getSachTacGia().add(stg);
    }
  }
}
