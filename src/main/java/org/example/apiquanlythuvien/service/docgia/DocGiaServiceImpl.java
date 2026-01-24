package org.example.apiquanlythuvien.service.docgia;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.DocGia;
import org.example.apiquanlythuvien.data.request.UpdateDocGiaAdminRequest;
import org.example.apiquanlythuvien.data.response.DocGiaResponseAdmin;
import org.example.apiquanlythuvien.data.response.DocGiaResponseUser;
import org.example.apiquanlythuvien.data.response.DocGiaWithOverdueResponse;
import org.example.apiquanlythuvien.defaults.Const;
import org.example.apiquanlythuvien.exception.NotFoundException;
import org.example.apiquanlythuvien.mapper.DocGiaMapper;
import org.example.apiquanlythuvien.responsitory.DocGiaRepository;
import org.example.apiquanlythuvien.responsitory.PhieuMuonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class DocGiaServiceImpl implements DocGiaService {

  private final DocGiaRepository docGiaRepository;
  private final PhieuMuonRepository phieuMuonRepository;
  private final DocGiaMapper docGiaMapper;

  @Override
  @Transactional
  public Page<DocGiaResponseAdmin> getAllDocGia(String trangThaiDocGia, Pageable pageable) {
    if (trangThaiDocGia == null || trangThaiDocGia.equals("TAT_CA")) {
      return docGiaRepository.findAll(pageable)
          .map(docGiaMapper::toDocGiaResponse);
    }
    return docGiaRepository.findAllByTrangThaiDocGia(trangThaiDocGia, pageable)
        .map(docGiaMapper::toDocGiaResponse);
  }

  @Override
  @Transactional
  public Optional<DocGiaResponseUser> getDocGiaById(Long accountId) {
    return docGiaRepository.findByAccountId(accountId);
  }

  @Override
  @Transactional
  public DocGiaResponseAdmin getDocGiaByDocGiaIdAdmin(Long docGiaId) {
    return docGiaRepository.findById(docGiaId)
        .map(docGiaMapper::toDocGiaResponse)
        .orElseThrow(() -> new NotFoundException("Doc gia khong tim thấy id: " + docGiaId));
  }

  @Override
  @Transactional
  public void updateDocGiaAdmin(UpdateDocGiaAdminRequest updateDocGiaAdminRequest) {
    Optional<DocGia> docGiaOptional = docGiaRepository.findById(updateDocGiaAdminRequest.getDocGiaId());
    if (docGiaOptional.isPresent()) {
      DocGia docGia = docGiaOptional.get();
      docGiaMapper.updateDocGiaRequestMapper(updateDocGiaAdminRequest, docGia);
      docGiaRepository.save(docGia);
    } else {
      throw new NotFoundException("Doc gia khong tim thấy id: " + updateDocGiaAdminRequest.getDocGiaId());
    }
  }

  @Override
  public long countDocGia() {
    return docGiaRepository.count();
  }

  @Override
  @Transactional
  public DocGiaWithOverdueResponse getDocGiaByTheThuVienId(Long theThuVienId) {
    DocGia docGia = docGiaRepository.findByTheThuVienId(theThuVienId)
        .orElseThrow(() -> new NotFoundException("Độc giả không tìm thấy với mã thẻ: " + theThuVienId));

    long overdueCount = phieuMuonRepository.countByTrangThaiPhieuMuonAndTheThuVienId(theThuVienId,
        Const.PHIEUMUON_OVERDUE);

    return DocGiaWithOverdueResponse.builder()
        .docGia(docGiaMapper.toDocGiaResponseAdmin(docGia))
        .overdueCount(overdueCount)
        .build();
  }
}
