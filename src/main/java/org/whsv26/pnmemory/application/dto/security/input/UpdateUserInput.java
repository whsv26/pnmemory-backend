package org.whsv26.pnmemory.application.dto.security.input;

import lombok.Data;
import javax.validation.constraints.Email;

@Data
public class UpdateUserInput {
  @Email
  private String username;

  private String password;
}
