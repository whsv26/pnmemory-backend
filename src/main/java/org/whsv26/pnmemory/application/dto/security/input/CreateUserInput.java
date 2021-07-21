package org.whsv26.pnmemory.application.dto.security.input;

import lombok.Data;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserInput {
  @Email @NotNull
  String username;

  @NotNull
  String password;

  @NotNull
  String rePassword;

  @AssertTrue(message = "passwords must be the same")
  public boolean isPasswordMatches() {
    return password.equals(rePassword);
  }
}
