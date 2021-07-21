package org.whsv26.pnmemory.application.controller;

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
import org.whsv26.pnmemory.application.dto.input.AuthInput;
import org.whsv26.pnmemory.application.dto.output.UserOutput;
import org.whsv26.pnmemory.infrastructure.service.security.JwtService;
import org.whsv26.pnmemory.domain.mapper.UserOutputMapper;
import org.whsv26.pnmemory.domain.model.User;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/public")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtTokenUtil;
    private final UserOutputMapper userOutputMapper;

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

//  @PostMapping("token")
//  public ResponseEntity<UserView> token(@RequestBody @Valid AuthInput input) {
//    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//        input.username(),
//        input.password()
//    );
//
//    Authentication authentication = authenticationManager.authenticate(token);
//    User user = (User) authentication.getPrincipal();
//
//    return ResponseEntity.ok()
//        .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
//        .body(userMapper.toUserView(user));
//  }
}
