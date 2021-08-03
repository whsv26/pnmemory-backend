package org.whsv26.pnmemory.application.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.whsv26.pnmemory.application.dto.security.input.RefreshFcmTokenInput;
import org.whsv26.pnmemory.domain.security.service.UserService;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api")
public class RefreshFcmTokenAction {
  private final UserService userService;

  @PutMapping("fcm/refresh")
  public ResponseEntity<?> execute(
      Principal principal,
      @RequestBody @Valid RefreshFcmTokenInput input) {

    userService.refreshFcmToken(input.getFcmToken(), principal.getName());

    return ResponseEntity.ok().build();
  }
}
