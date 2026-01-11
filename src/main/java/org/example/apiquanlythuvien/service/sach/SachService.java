package org.example.apiquanlythuvien.service.sach;

import org.example.apiquanlythuvien.data.response.SachResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SachService {

  Page<SachResponse> getAllSach(Pageable pageable);
}
