package org.whsv26.pnmemory.domain.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "fcm_tokens")
@NoArgsConstructor
@Getter
public class FcmToken {
  @Id
  @GeneratedValue
  private UUID id;

  public FcmToken(User user, String token) {
    this.user = user;
    this.token = token;
  }

  @NotNull
  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  @Setter
  @NotNull
  private String token;
}
