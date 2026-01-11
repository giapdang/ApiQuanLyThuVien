package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.TacGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacGiaRepository extends JpaRepository<TacGia, Long> {

}
