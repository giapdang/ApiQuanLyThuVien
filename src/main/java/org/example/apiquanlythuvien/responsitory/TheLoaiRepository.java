package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai, Long> {

}
