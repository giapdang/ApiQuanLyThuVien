package org.example.apiquanlythuvien.responsitory;

import java.util.Optional;
import org.example.apiquanlythuvien.data.entity.DocGia;
import org.example.apiquanlythuvien.data.response.DocGiaResponseUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocGiaRepository extends JpaRepository<DocGia, Long> {

  @Query("SELECT new org.example.apiquanlythuvien.data.response.DocGiaResponseUser(" +
      "d.docGiaId, d.tenDocGia, d.email, d.soDienThoai, d.diaChi, d.ngaySinh, " +
      "d.tienKyQuy, d.trangThaiDocGia, t.theThuVienId, t.ngayHetHan, t.trangThai, t.ngayCap) " +
      "FROM DocGia d LEFT JOIN d.theThuVien t " +
      "WHERE d.account.accountId = :accountId")
  Optional<DocGiaResponseUser> findByAccountId(@Param("accountId") Long accountId);

  Page<DocGia> findAllByTrangThaiDocGia(String trangThaiDocGia, Pageable pageable);

  @Query("SELECT d FROM DocGia d WHERE d.theThuVien.theThuVienId = :theThuVienId")
  Optional<DocGia> findByTheThuVienId(@Param("theThuVienId") Long theThuVienId);
}
