package org.example.apiquanlythuvien.service.phieumuon;

import org.example.apiquanlythuvien.data.request.CreatePhieuMuonRequest;
import org.example.apiquanlythuvien.data.response.PhieuMuonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhieuMuonService {

    void createPhieuMuon(CreatePhieuMuonRequest request);

    Page<PhieuMuonResponse> getPhieuMuonByTrangThai(String trangThai, Pageable pageable);
}
