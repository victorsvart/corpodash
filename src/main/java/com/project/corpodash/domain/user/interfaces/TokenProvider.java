package com.project.corpodash.domain.user.interfaces;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

  String makeToken(Authentication authentication);

  boolean validateToken(String token);

  Authentication setAuthentication(String token);
}
