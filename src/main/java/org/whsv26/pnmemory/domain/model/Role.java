package org.whsv26.pnmemory.domain.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
  public enum RoleEnum {ROLE_USER}
  private final String authority = RoleEnum.ROLE_USER.toString();

  @Override
  public String getAuthority() {
    return authority;
  }
}
