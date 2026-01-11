package org.example.apiquanlythuvien.service.docgia;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.response.DocGiaResponseAdmin;
import org.example.apiquanlythuvien.data.response.DocGiaResponseUser;
import org.example.apiquanlythuvien.mapper.DocGiaMapper;
import org.example.apiquanlythuvien.responsitory.DocGiaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class DocGiaServiceImpl implements  DocGiaService {

  private final DocGiaRepository docGiaRepository;
  private final DocGiaMapper docGiaMapper;

  @Override
  @Transactional
  public Page<DocGiaResponseAdmin> getAllDocGia(Pageable pageable) {
    return docGiaRepository.findAll(pageable)
        .map(docGiaMapper::toDocGiaResponse);
  }

  @Override
  @Transactional
  public Optional<DocGiaResponseUser> getDocGiaById(Long accountId) {
    return docGiaRepository.findByAccountId(accountId);
  }
}
