package org.example.apiquanlythuvien.service.docgia;

import java.util.Optional;
import org.example.apiquanlythuvien.data.response.DocGiaResponseAdmin;
import org.example.apiquanlythuvien.data.response.DocGiaResponseUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocGiaService {

  Page<DocGiaResponseAdmin> getAllDocGia(Pageable pageable);

  Optional<DocGiaResponseUser> getDocGiaById(Long accountId);
}
