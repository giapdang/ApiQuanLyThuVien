package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.NhaXuatBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhaXuatBanRepository extends JpaRepository<NhaXuatBan, Long> {

}
