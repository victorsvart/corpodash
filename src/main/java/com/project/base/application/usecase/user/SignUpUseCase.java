package com.project.base.application.usecase.user;

import com.project.base.application.dto.user.AuthDto;
import com.project.base.domain.email.Email;
import com.project.base.domain.role.Role;
import com.project.base.domain.user.User;
import com.project.base.infrastructure.persistence.user.UserRepository;

import jakarta.persistence.EntityExistsException;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpUseCase {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SignUpUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void execute(AuthDto.SignUpRequest request) {
    if (userRepository.findByEmail(Email.of(request.email)).isPresent()) {
      throw new EntityExistsException("User with this email already exists");
    }

    User user = new User.Builder()
        .name(request.name)
        .email(request.email)
        .password(passwordEncoder.encode(request.password))
        .roles(Set.of(Role.ADMIN, Role.USER))
        .build();

    userRepository.save(user);
  }
}
