package org.whsv26.pnmemory.domain.security.service;

import org.whsv26.pnmemory.application.dto.security.input.CreateUserInput;
import org.whsv26.pnmemory.application.dto.security.input.UpdateUserInput;
import org.whsv26.pnmemory.domain.security.model.User;

public interface UserService {
  User create(CreateUserInput input);
  User update(UpdateUserInput input);
}
