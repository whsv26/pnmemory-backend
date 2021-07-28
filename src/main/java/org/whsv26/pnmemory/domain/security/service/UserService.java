package org.whsv26.pnmemory.domain.security.service;

import org.whsv26.pnmemory.application.dto.security.input.CreateUserInput;
import org.whsv26.pnmemory.application.dto.security.input.UpdateUserInput;
import org.whsv26.pnmemory.domain.security.model.User;

import java.util.Optional;

public interface UserService {
  User create(CreateUserInput input);
  Optional<User> update(UpdateUserInput input, String username);
}
