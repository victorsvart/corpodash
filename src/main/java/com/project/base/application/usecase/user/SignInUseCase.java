package com.project.base.application.usecase.user;

import com.project.base.application.dto.user.AuthDto;
import com.project.base.infrastructure.token.TokenProvider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SignInUseCase {

  private final AuthenticationManager authenticationManager;
  private final TokenProvider tokenProvider;
  // private final UserRepository userRepository;

  public SignInUseCase(AuthenticationManager authenticationManager,
      TokenProvider tokenProvider) {
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
    // this.userRepository = userRepository;
  }

  public AuthDto.AuthResponse execute(AuthDto.SignInRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email, request.password));

    String jwtToken = tokenProvider.makeToken(authentication);
    return new AuthDto.AuthResponse(jwtToken);
  }
}
