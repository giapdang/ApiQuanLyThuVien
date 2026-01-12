package org.example.apiquanlythuvien.service.tacgia;

import org.example.apiquanlythuvien.data.request.CreateTacGiaAdminRequest;
import org.example.apiquanlythuvien.data.response.TacGiaAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TacGiaService {

  Page<TacGiaAdminResponse> getAllTacGia(Pageable pageable);

  void createTacGia(CreateTacGiaAdminRequest request);
}
