package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.LinhVuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhVucRepository extends JpaRepository<LinhVuc, Long> {

}
