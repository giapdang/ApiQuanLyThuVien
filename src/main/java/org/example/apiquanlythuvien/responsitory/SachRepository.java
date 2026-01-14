package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.Sach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SachRepository extends JpaRepository<Sach, Long> {


  @Query("SELECT s FROM Sach s WHERE s.theLoai.theLoaiId = :theLoaiId")
  Page<Sach> getAllSachByTheLoaiId(Long theLoaiId,Pageable pageable);
}
