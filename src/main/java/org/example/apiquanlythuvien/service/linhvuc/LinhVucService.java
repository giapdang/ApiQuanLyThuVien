package org.example.apiquanlythuvien.service.linhvuc;

import org.example.apiquanlythuvien.data.request.CreateLinhVucAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateLinhVucAdminRequest;
import org.example.apiquanlythuvien.data.response.LinhVucAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LinhVucService {

    Page<LinhVucAdminResponse> getAllLinhVuc(Pageable pageable);

    void createLinhVuc(CreateLinhVucAdminRequest request);

    void updateLinhVuc(UpdateLinhVucAdminRequest request);

    void deleteLinhVuc(Long id);
}
