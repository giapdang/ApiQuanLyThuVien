package org.example.apiquanlythuvien.service.phieumuon;

import java.util.List;
import org.example.apiquanlythuvien.data.request.CreatePhieuMuonRequest;
import org.example.apiquanlythuvien.data.response.ChiTietMuonTraResponse;
import org.example.apiquanlythuvien.data.response.PhieuMuonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhieuMuonService {

    void createPhieuMuon(CreatePhieuMuonRequest request);

    Page<PhieuMuonResponse> getPhieuMuonByTrangThai(String trangThai, Pageable pageable);

    List<ChiTietMuonTraResponse> getChiTietMuonTraByPhieuMuonId(Long phieuMuonId);
}
