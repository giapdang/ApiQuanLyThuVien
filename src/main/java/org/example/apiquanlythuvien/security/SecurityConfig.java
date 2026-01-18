package org.example.apiquanlythuvien.security;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.defaults.Const;
import org.example.apiquanlythuvien.service.account.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .cors(Customizer.withDefaults())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/accounts/login", "/api/sach/all", "/api/sach/search", "/api/sach/chitietsach",
                "/api/bansaosach/danhsach", "/api/sach/theloai")
            .permitAll()
            .requestMatchers("/api/docgia/chitietdocgia", "/api/sach/theloai", "/api/cart/**",
                "/api/phieumuon/create", "/api/phieumuon/load")
            .hasAuthority(Const.ROLE_USER)
            .requestMatchers("/api/accounts/**", "/api/docgia/**", "/api/tacgia/**", "/api/tacgia/**",
                "/api/nhaxuatban/**", "/api/theloai/**", "/api/linhvuc/**", "/api/thethuvien/**",
                "/api/sach/admin/**", "/api/phieumuon/admin/**")
            .hasAuthority(Const.ROLE_ADMIN)
            .anyRequest().authenticated())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
