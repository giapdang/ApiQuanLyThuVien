package org.example.apiquanlythuvien.responsitory;

import org.example.apiquanlythuvien.data.entity.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SachRepository extends JpaRepository<Sach, Long> {

}
