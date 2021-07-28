package org.whsv26.pnmemory.application.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.whsv26.pnmemory.application.dto.security.input.UpdateUserInput;
import org.whsv26.pnmemory.application.dto.security.output.UserOutput;
import org.whsv26.pnmemory.application.mapper.security.UserOutputMapper;
import org.whsv26.pnmemory.domain.security.service.UserService;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api")
public class UpdateUserAction {
  private final UserOutputMapper userOutputMapper;
  private final UserService userService;

  @PatchMapping("users")
  public ResponseEntity<UserOutput> execute(
      Principal principal,
      @RequestBody @Valid UpdateUserInput input) {

    return userService.update(input, principal.getName())
        .map(userOutputMapper::toUserOutput)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
