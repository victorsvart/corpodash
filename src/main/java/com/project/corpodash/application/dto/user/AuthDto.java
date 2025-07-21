package com.project.corpodash.application.dto.user;

public class AuthDto {

  public static class SignUpRequest {
    public String name;
    public String email;
    public String password;
  }

  public static class SignInRequest {
    public String email;
    public String password;
  }

  public static class AuthResponse {
    public String token;

    public AuthResponse(String token) {
      this.token = token;
    }
  }
}
