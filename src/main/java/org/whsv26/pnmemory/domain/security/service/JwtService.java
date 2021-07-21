package org.whsv26.pnmemory.domain.security.service;

import org.springframework.stereotype.Component;
import org.whsv26.pnmemory.domain.security.model.User;
import java.util.Date;

@Component
public interface JwtService {
  String generateAccessToken(User user);
  String getUserName(String token);
  Date getExpirationDate(String token);
  boolean validate(String token);
}
