package org.whsv26.pnmemory.infrastructure.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.whsv26.pnmemory.domain.model.User;
import io.jsonwebtoken.Jwts;
import java.util.Date;

@Component
public class JwtServiceImpl implements JwtService {
  private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";

  @Override
  public String generateAccessToken(User user) {
    return Jwts.builder()
        .setSubject(String.format("%s", user.getUsername()))
        .setIssuer("whsv26.org")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  @Override
  public String getUserName(String token) {
    return parseClaims(token).getSubject();
  }

  @Override
  public Date getExpirationDate(String token) {
    return parseClaims(token).getExpiration();
  }

  @Override
  public boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }

  private Claims parseClaims(String token) {
    return Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();
  }
}
