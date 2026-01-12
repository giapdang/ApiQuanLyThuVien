package org.example.apiquanlythuvien.service.account;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.example.apiquanlythuvien.data.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private final String SECRET_KEY = "ZGF5IGxhIGR1IGFuIGJhaSB0YXAgbG9uIGNvIGJhbiBjdWEgc2luaCB2aWVuIGNudHQ=";

  public String generateToken(Account account) {
    return Jwts.builder()
        .setSubject(account.getUsername())
        .claim("role", account.getRole())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 ngày
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }
}
