package org.whsv26.pnmemory.domain.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.whsv26.pnmemory.domain.model.Role.RoleEnum.ROLE_USER;

/**
 * "whsv26@gmail.com"
 * "$2y$10$pF3W3HWndTMauE9tog4jFOM4dlSVTff0GiDOTcJyYjHCpW9if7GH2"
 */
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue
  private UUID id;

  private String username;

  private String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<Role> authorities = new HashSet<>();
    authorities.add(new Role(ROLE_USER));

    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
