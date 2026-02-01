package org.example.apiquanlythuvien.responsitory;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.example.apiquanlythuvien.data.entity.ChiTietMuonTra;
import org.example.apiquanlythuvien.data.entity.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietMuonTraRepository extends JpaRepository<ChiTietMuonTra, Long> {

    List<ChiTietMuonTra> findByPhieuMuonPhieuMuonId(Long phieuMuonId);

    List<ChiTietMuonTra> findByTinhTrangKhiTraAndHanTraBefore(String tinhTrang, Date date);

    @Query("SELECT ct FROM ChiTietMuonTra ct WHERE ct.tinhTrangKhiTra IN :statuses AND ct.hanTra < :date")
    List<ChiTietMuonTra> findOverdue(@Param("statuses") List<String> statuses, @Param("date") Date date);

    @Query("SELECT dg FROM ChiTietMuonTra ct " +
        "JOIN ct.phieuMuon pm " +
        "JOIN pm.theThuVien ttv " +
        "JOIN ttv.docGia dg " +
        "WHERE ct.chiTietMuonTraId = :chiTietId")
    Optional<DocGia> findDocGiaByChiTietMuonTraId(@Param("chiTietId") Long chiTietId);
}
