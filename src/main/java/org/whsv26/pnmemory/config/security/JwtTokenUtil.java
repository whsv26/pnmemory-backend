package org.whsv26.pnmemory.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.whsv26.pnmemory.domain.model.User;
import io.jsonwebtoken.Jwts;
import java.util.Date;

@Component
public class JwtTokenUtil {
  private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
  private final String jwtIssuer = "whsv26";

  public String generateAccessToken(User user) {
    return Jwts.builder()
        .setSubject(String.format("%s", user.getUsername()))
        .setIssuer(jwtIssuer)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public Claims parseClaims(String token) {
    return Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();
  }

  public String getUserName(String token) {
    return parseClaims(token).getSubject();
  }

  public Date getExpirationDate(String token) {
    return parseClaims(token).getExpiration();
  }

  public boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}
