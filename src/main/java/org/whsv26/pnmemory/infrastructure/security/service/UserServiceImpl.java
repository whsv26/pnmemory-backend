package org.whsv26.pnmemory.infrastructure.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.whsv26.pnmemory.application.dto.security.input.CreateUserInput;
import org.whsv26.pnmemory.application.dto.security.input.UpdateUserInput;
import org.whsv26.pnmemory.domain.security.service.UserService;
import org.whsv26.pnmemory.application.mapper.security.UserInputMapper;
import org.whsv26.pnmemory.domain.security.model.User;
import org.whsv26.pnmemory.domain.security.repository.UserRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserInputMapper userInputMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User create(CreateUserInput input) {
    User user = userInputMapper.create(input);
    String plainPassword = user.getPassword();
    user.setPassword(passwordEncoder.encode(plainPassword));

    return userRepository.save(user);
  }

  @Override
  public Optional<User> update(UpdateUserInput input, String username) {
    return userRepository.findByUsername(username)
        .map((user) -> userInputMapper.update(input, user))
        .map((user) -> {
          String plainPass = input.getPassword();
          String hashedPass = plainPass.equals(user.getPassword())
              ? passwordEncoder.encode(user.getPassword())
              : user.getPassword();

          user.setPassword(hashedPass);
          userRepository.save(user);

          return user;
        });
  }
}
