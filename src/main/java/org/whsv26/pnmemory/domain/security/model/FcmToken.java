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

  @NotNull
  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  @Setter
  @NotNull
  @Column(length = 500)
  private String token;

  public FcmToken(User user, String token) {
    this.user = user;
    this.token = token;
  }
}
