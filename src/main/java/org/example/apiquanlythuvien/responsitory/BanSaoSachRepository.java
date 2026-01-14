package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.BanSaoSach;
import org.example.apiquanlythuvien.data.response.BanSaoSachResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BanSaoSachRepository extends JpaRepository<BanSaoSach, Long> {

  @Query("SELECT new org.example.apiquanlythuvien.data.response.BanSaoSachResponse(b.banSaoSachId,b.tinhTrangBanSaoSach,b.trangThaiBanSaoSach) " +
      "FROM BanSaoSach b " +
      "WHERE b.sach.sachId = :sachId")
  Page<BanSaoSachResponse> findAllBanSaoSachBySachId(@Param("sachId") Long sachId, Pageable pageable);

}
