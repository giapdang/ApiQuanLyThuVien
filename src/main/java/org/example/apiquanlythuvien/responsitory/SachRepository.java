package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.Sach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SachRepository extends JpaRepository<Sach, Long> {

  @Query("SELECT s FROM Sach s WHERE s.theLoai.tenTheLoai = :tenTheLoai")
  Page<Sach> getAllSachByTenTheLoai(String tenTheLoai, Pageable pageable);

  @Query("SELECT DISTINCT s FROM Sach s " +
      "LEFT JOIN s.sachTacGia stg " +
      "LEFT JOIN stg.tacGia tg " +
      "WHERE LOWER(s.tenSach) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
      "OR LOWER(s.nhaXuatBan.tenNhaXuatBan) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
      "OR LOWER(s.theLoai.tenTheLoai) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
      "OR LOWER(s.linhVuc.tenLinhVuc) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
      "OR LOWER(tg.tenTacGia) LIKE LOWER(CONCAT('%', :keyword, '%'))")
  Page<Sach> searchByKeyword(String keyword, Pageable pageable);

  @Query("SELECT DISTINCT s FROM Sach s " +
      "LEFT JOIN s.banSaoSach bss " +
      "WHERE (:trangThai IS NULL OR bss.trangThaiBanSaoSach = :trangThai)")
  Page<Sach> findAllAdmin(String trangThai, Pageable pageable);

  @Query("SELECT DISTINCT s FROM Sach s " +
      "LEFT JOIN s.sachTacGia stg " +
      "LEFT JOIN stg.tacGia tg " +
      "LEFT JOIN s.banSaoSach bss " +
      "WHERE ((LOWER(s.tenSach) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
      "OR (LOWER(s.nhaXuatBan.tenNhaXuatBan) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
      "OR (LOWER(s.theLoai.tenTheLoai) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
      "OR (LOWER(s.linhVuc.tenLinhVuc) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
      "OR (LOWER(tg.tenTacGia) LIKE LOWER(CONCAT('%', :keyword, '%')))) " +
      "AND (:trangThai IS NULL OR bss.trangThaiBanSaoSach = :trangThai)")
  Page<Sach> searchByKeywordAdmin(String keyword, String trangThai, Pageable pageable);
}
