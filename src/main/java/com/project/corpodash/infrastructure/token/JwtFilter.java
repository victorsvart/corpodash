package com.project.corpodash.infrastructure.token;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT authentication filter that processes incoming HTTP requests once per
 * request.
 *
 * <p>
 * Extracts JWT tokens from the "Authorization" header (Bearer token) or from
 * cookies, validates
 * the token, and sets the authentication in the security context if valid.
 */
public class JwtFilter extends OncePerRequestFilter {
  private static final String AUTHORIZATION_HEADER = "Authorization";

  private static final String BEARER_PREFIX = "Bearer ";

  private final JwtTokenProvider tokenProvider;

  public JwtFilter(JwtTokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  private String extractToken(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) {
      return token.substring(7);
    }

    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if ("TOKEN".equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }

    return null;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    String token = extractToken(request);
    if (token != null && tokenProvider.validateToken(token)) {
      Authentication authentication = tokenProvider.setAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
