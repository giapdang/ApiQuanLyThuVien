package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.PhieuMuon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, Long> {

        @Query("SELECT pm FROM PhieuMuon pm WHERE " +
                        "pm.theThuVien.theThuVienId = :theThuVienId AND " +
                        "(:trangThai IS NULL OR :trangThai = 'TAT_CA' OR pm.trangThaiPhieuMuon = :trangThai)")
        Page<PhieuMuon> findByTheThuVienIdAndTrangThai(
                        @Param("theThuVienId") Long theThuVienId,
                        @Param("trangThai") String trangThai,
                        Pageable pageable);

        @Query("SELECT pm FROM PhieuMuon pm WHERE " +
                        "(:trangThai IS NULL OR :trangThai = 'TAT_CA' OR pm.trangThaiPhieuMuon = :trangThai)")
        Page<PhieuMuon> findByTrangThai(
                        @Param("trangThai") String trangThai,
                        Pageable pageable);

        @Query("SELECT pm FROM PhieuMuon pm WHERE " +
                        "(:keyword IS NULL OR :keyword = '' OR " +
                        "LOWER(pm.theThuVien.docGia.tenDocGia) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "CAST(pm.theThuVien.theThuVienId AS string) LIKE CONCAT('%', :keyword, '%')) AND " +
                        "(:trangThai IS NULL OR :trangThai = 'TAT_CA' OR pm.trangThaiPhieuMuon = :trangThai)")
        Page<PhieuMuon> searchPhieuMuonAdmin(
                        @Param("keyword") String keyword,
                        @Param("trangThai") String trangThai,
                        Pageable pageable);

        long countByTrangThaiPhieuMuon(String trangThaiPhieuMuon);

        @Query("SELECT COUNT(pm) FROM PhieuMuon pm WHERE pm.theThuVien.theThuVienId = :theThuVienId AND pm.trangThaiPhieuMuon = :trangThai")
        long countByTrangThaiPhieuMuonAndTheThuVienId(
                        @Param("theThuVienId") Long theThuVienId,
                        @Param("trangThai") String trangThai);

}
