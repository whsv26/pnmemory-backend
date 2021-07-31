package org.whsv26.pnmemory.domain.security.service;

import org.whsv26.pnmemory.application.dto.security.input.CreateUserInput;
import org.whsv26.pnmemory.application.dto.security.input.UpdateUserInput;
import org.whsv26.pnmemory.domain.security.model.FcmToken;
import org.whsv26.pnmemory.domain.security.model.User;
import java.util.Optional;

public interface UserService {

  /**
   * Create new user from input.
   */
  User create(CreateUserInput input);

  /**
   * Find user by username
   * and update profile.
   */
  Optional<User> update(UpdateUserInput input, String username);

  /**
   * Create or replace user FCM token.
   * FCM - firebase cloud messaging.
   */
  Optional<FcmToken> refreshFcmToken(String token, String username);
}
