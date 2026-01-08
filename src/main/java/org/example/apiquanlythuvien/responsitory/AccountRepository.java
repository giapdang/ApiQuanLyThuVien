package org.example.apiquanlythuvien.responsitory;

import java.util.Optional;
import org.example.apiquanlythuvien.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  boolean existsByUsername(String username);

  Optional<Account> findByUsername(String username);
}
