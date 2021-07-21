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
  public User update(UpdateUserInput input) {
    // TODO
    return null;
  }
}
