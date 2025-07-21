package com.project.corpodash.application.controller.user;

import com.project.corpodash.application.dto.user.AuthDto;
import com.project.corpodash.application.dto.user.UserPresenter;
import com.project.corpodash.application.usecase.user.GetAllUsersUseCase;
import com.project.corpodash.application.usecase.user.SignInUseCase;
import com.project.corpodash.application.usecase.user.SignUpUseCase;
import com.project.corpodash.domain.user.User;
import com.project.corpodash.infrastructure.token.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final SignUpUseCase signUpUseCase;
  private final SignInUseCase signInUseCase;
  private final GetAllUsersUseCase getAllUsersUseCase;

  public UserController(
      SignUpUseCase signUpUseCase,
      SignInUseCase signInUseCase,
      GetAllUsersUseCase getAllUsersUseCase) {
    this.signUpUseCase = signUpUseCase;
    this.signInUseCase = signInUseCase;
    this.getAllUsersUseCase = getAllUsersUseCase;
  }

  @GetMapping("/me")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> me() {
    return ResponseEntity.ok("");
  }

  @PostMapping("/signUp")
  public ResponseEntity<?> signUp(@RequestBody AuthDto.SignUpRequest request) {
    signUpUseCase.execute(request);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/signIn")
  public ResponseEntity<?> signIn(@RequestBody AuthDto.SignInRequest request) {
    var token = signInUseCase.execute(request);
    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, JwtUtil.makeCookieString(token.token))
        .body(token);
  }

  @GetMapping("/getAllUsers")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<UserPresenter>> getAllUsers() {
    List<User> users = getAllUsersUseCase.execute();
    return ResponseEntity.ok(UserPresenter.ofMany(users));
  }

  @GetMapping("/logout")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> logout(HttpServletResponse response) {
    response.addHeader("Set-Cookie", JwtUtil.makeEmptyCookieString());
    return ResponseEntity.ok("logged out");
  }
}
