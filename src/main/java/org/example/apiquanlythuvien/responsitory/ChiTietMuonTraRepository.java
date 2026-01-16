package org.example.apiquanlythuvien.responsitory;

import java.util.List;
import org.example.apiquanlythuvien.data.entity.ChiTietMuonTra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietMuonTraRepository extends JpaRepository<ChiTietMuonTra, Long> {

    List<ChiTietMuonTra> findByPhieuMuonPhieuMuonId(Long phieuMuonId);
}
