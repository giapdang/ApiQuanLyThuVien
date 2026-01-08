package org.example.apiquanlythuvien.service.account;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.entity.Account;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

  private final Account account;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(account.getRole()));
  }

  @Override
  public @Nullable String getPassword() {
    return account.getPassword();
  }

  @Override
  public String getUsername() {
    return account.getUsername();
  }

  public Account getAccount() {
    return account;
  }
}
