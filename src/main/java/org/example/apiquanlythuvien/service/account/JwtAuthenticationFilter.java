package org.example.apiquanlythuvien.service.account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.Account;
import org.example.apiquanlythuvien.responsitory.AccountRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final AccountRepository accountRepository;
  private final String SECRET_KEY = "ZGF5IGxhIGR1IGFuIGJhaSB0YXAgbG9uIGNvIGJhbiBjdWEgc2luaCB2aWVuIGNudHQ=";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);

      try {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();

        String username = claims.getSubject();

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          Account account = accountRepository.findByUsername(username)
              .orElseThrow(() -> new RuntimeException("User not found"));

          CustomUserDetails userDetails = new CustomUserDetails(account);

          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);

          log.info("User {} authenticated successfully", username);
        }
      } catch (Exception e) {
        log.error("JWT validation failed: {}", e.getMessage());
      }
    }

    filterChain.doFilter(request, response);
  }
}
