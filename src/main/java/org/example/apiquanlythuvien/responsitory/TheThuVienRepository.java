package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.TheThuVien;
import org.example.apiquanlythuvien.data.response.ThethuVienAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TheThuVienRepository extends JpaRepository<TheThuVien, Long> {

  @Query("SELECT new org.example.apiquanlythuvien.data.response.ThethuVienAdminResponse(" +
      "t.theThuVienId, t.ngayHetHan, t.trangThai, t.soLuongSachDuocMuon, t.ngayCap, d.tenDocGia) " +
      "FROM TheThuVien t " +
      "JOIN t.docGia d " +
      "WHERE (:trangThai IS NULL OR t.trangThai = :trangThai)")
  Page<ThethuVienAdminResponse> findAllTheThuVienAdmin(String trangThai, Pageable pageable);
}
