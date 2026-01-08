package org.example.apiquanlythuvien.service.account;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.Account;
import org.example.apiquanlythuvien.responsitory.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByUsername(username)
        .orElseThrow(() -> {
          log.error("User with username {} not found", username);
          return new UsernameNotFoundException("User not found");
        });
    return new CustomUserDetails(account);
  }
}
