package com.project.corpodash.application.usecase.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.project.corpodash.application.base.Usecase;
import com.project.corpodash.application.dto.user.AuthDto;
import com.project.corpodash.application.service.entityManager.EntityManagerService;
import com.project.corpodash.domain.user.interfaces.TokenProvider;

@Service
public class SignInUseCase extends Usecase<AuthDto.AuthResponse, AuthDto.SignInRequest> {

  private final AuthenticationManager authenticationManager;
  private final TokenProvider tokenProvider;

  public SignInUseCase(EntityManagerService ems, AuthenticationManager authenticationManager,
      TokenProvider tokenProvider) {
    super(ems);
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
  }

  public AuthDto.AuthResponse execute(AuthDto.SignInRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email, request.password));

    String jwtToken = tokenProvider.makeToken(authentication);
    return new AuthDto.AuthResponse(jwtToken);
  }
}
