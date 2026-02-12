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

    @Query("SELECT ct FROM ChiTietMuonTra ct WHERE ct.tinhTrangKhiTra IN :statuses AND ct.hanTra < :date")
    List<ChiTietMuonTra> findOverdue(@Param("statuses") List<String> statuses, @Param("date") Date date);

    @Query("SELECT dg FROM ChiTietMuonTra ct " +
        "JOIN ct.phieuMuon pm " +
        "JOIN pm.theThuVien ttv " +
        "JOIN ttv.docGia dg " +
        "WHERE ct.chiTietMuonTraId = :chiTietId")
    Optional<DocGia> findDocGiaByChiTietMuonTraId(@Param("chiTietId") Long chiTietId);

    @Query("SELECT DISTINCT bss.sach.sachId FROM ChiTietMuonTra ct " +
        "JOIN ct.banSaoSach bss " +
        "JOIN ct.phieuMuon pm " +
        "JOIN pm.theThuVien ttv " +
        "WHERE ttv.docGia.docGiaId = :docGiaId")
    List<Long> findBorrowedBookIdsByDocGiaId(@Param("docGiaId") Long docGiaId);

    @Query("SELECT s.theLoai.tenTheLoai, COUNT(s) FROM ChiTietMuonTra ct " +
        "JOIN ct.banSaoSach bss " +
        "JOIN bss.sach s " +
        "JOIN ct.phieuMuon pm " +
        "JOIN pm.theThuVien ttv " +
        "WHERE ttv.docGia.docGiaId = :docGiaId " +
        "AND ct.tinhTrangKhiTra IN ('DA_TRA', 'DANG_MUON') " +
        "GROUP BY s.theLoai.tenTheLoai")
    List<Object[]> getGenreFrequencyByUser(@Param("docGiaId") Long docGiaId);

    @Query("SELECT s.linhVuc.tenLinhVuc, COUNT(s) FROM ChiTietMuonTra ct " +
        "JOIN ct.banSaoSach bss " +
        "JOIN bss.sach s " +
        "JOIN ct.phieuMuon pm " +
        "JOIN pm.theThuVien ttv " +
        "WHERE ttv.docGia.docGiaId = :docGiaId " +
        "AND ct.tinhTrangKhiTra IN ('DA_TRA', 'DANG_MUON')" +
        "GROUP BY s.linhVuc.tenLinhVuc")
    List<Object[]> getFieldFrequencyByUser(@Param("docGiaId") Long docGiaId);

    @Query("SELECT DISTINCT tg.tenTacGia FROM ChiTietMuonTra ct " +
        "JOIN ct.banSaoSach bss " +
        "JOIN bss.sach s " +
        "JOIN s.sachTacGia stg " +
        "JOIN stg.tacGia tg " +
        "JOIN ct.phieuMuon pm " +
        "JOIN pm.theThuVien ttv " +
        "WHERE ttv.docGia.docGiaId = :docGiaId " +
        "AND ct.tinhTrangKhiTra IN ('DA_TRA', 'DANG_MUON')")
    List<String> getFavoriteAuthorsByUser(@Param("docGiaId") Long docGiaId);
}
