package org.whsv26.pnmemory.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.whsv26.pnmemory.domain.security.repository.UserRepository;
import org.whsv26.pnmemory.infrastructure.security.service.JwtServiceImpl;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.util.List.of;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final JwtServiceImpl jwtTokenUtil;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    getAuthorizationHeader(request)
        .map((hdr) -> hdr.replace("Bearer ", ""))
        .flatMap(this::validateToken)
        .map(jwtTokenUtil::getUserName)
        .flatMap(userRepository::findByUsername)
        .map((principal) -> new UsernamePasswordAuthenticationToken(
            principal, null, of()
        ))
        .map((authentication) -> {
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
          return authentication;
        });

    filterChain.doFilter(request, response);
  }

  private Optional<String> getAuthorizationHeader(HttpServletRequest request) {
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    return isEmpty(header) || !header.startsWith("Bearer ")
        ? Optional.empty()
        : Optional.of(header);
  }

  private Optional<String> validateToken(String token) {
    return jwtTokenUtil.validate(token)
      ? Optional.of(token)
      : Optional.empty();
  }
}
