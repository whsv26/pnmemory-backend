package org.whsv26.pnmemory.domain.security.model;

import lombok.Data;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

import static org.whsv26.pnmemory.domain.security.model.Role.RoleEnum.ROLE_USER;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue
  private UUID id;

  @NotNull
  @Column(unique = true)
  private String username;

  @NotNull
  private String password;

  @OneToOne(mappedBy = "user")
  private FcmToken fcmToken;

  public FcmToken getFcmToken() {
    return fcmToken;
  }

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
