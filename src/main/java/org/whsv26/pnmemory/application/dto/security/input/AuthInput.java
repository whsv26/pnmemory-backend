package org.whsv26.pnmemory.application.dto.security.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record AuthInput(
    @Email @NotNull
    String username,

    @NotNull
    String password
) {

}
