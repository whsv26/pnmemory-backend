package org.whsv26.pnmemory.application.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whsv26.pnmemory.application.dto.security.input.CreateUserInput;
import org.whsv26.pnmemory.application.dto.security.output.UserOutput;
import org.whsv26.pnmemory.application.mapper.security.UserOutputMapper;
import org.whsv26.pnmemory.domain.security.model.User;
import org.whsv26.pnmemory.domain.security.service.UserService;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/public")
public class RegisterAction {
  private final UserOutputMapper userOutputMapper;
  private final UserService userService;

  @PostMapping("register")
  public ResponseEntity<UserOutput> execute(@RequestBody @Valid CreateUserInput input) {
    User user = userService.create(input);
    UserOutput output = userOutputMapper.toUserOutput(user);

    return ResponseEntity.ok(output);
  }
}
