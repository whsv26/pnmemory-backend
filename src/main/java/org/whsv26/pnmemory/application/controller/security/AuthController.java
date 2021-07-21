package org.whsv26.pnmemory.application.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whsv26.pnmemory.application.dto.security.input.AuthInput;
import org.whsv26.pnmemory.application.dto.security.input.CreateUserInput;
import org.whsv26.pnmemory.application.dto.security.output.UserOutput;
import org.whsv26.pnmemory.domain.security.service.UserService;
import org.whsv26.pnmemory.domain.security.service.JwtService;
import org.whsv26.pnmemory.application.mapper.security.UserOutputMapper;
import org.whsv26.pnmemory.domain.security.model.User;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/public")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtTokenUtil;
  private final UserOutputMapper userOutputMapper;
  private final UserService userService;

  @PostMapping("token")
  public ResponseEntity<UserOutput> token(@RequestBody @Valid AuthInput input) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        input.username(),
        input.password()
    );

    Authentication authentication = authenticationManager.authenticate(token);
    User user = (User) authentication.getPrincipal();

    return ResponseEntity.ok()
        .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
        .body(userOutputMapper.toUserOutput(user));
  }

  @PostMapping("register")
  public ResponseEntity<UserOutput> register(@RequestBody @Valid CreateUserInput input) {
    User user = userService.create(input);
    UserOutput output = userOutputMapper.toUserOutput(user);

    return ResponseEntity.ok(output);
  }
}
