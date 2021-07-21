package org.whsv26.pnmemory.domain.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class Role implements GrantedAuthority {
  public enum RoleEnum { ROLE_USER }
  private final RoleEnum authority;

  @Override
  public String getAuthority() {
    return authority.toString();
  }
}
