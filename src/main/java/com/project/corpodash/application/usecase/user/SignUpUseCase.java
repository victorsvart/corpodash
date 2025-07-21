package com.project.corpodash.application.usecase.user;

import jakarta.persistence.EntityExistsException;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.corpodash.application.base.Usecase;
import com.project.corpodash.application.dto.user.AuthDto;
import com.project.corpodash.application.service.entityManager.EntityManagerService;
import com.project.corpodash.domain.base.valueobject.Email;
import com.project.corpodash.domain.role.Role;
import com.project.corpodash.domain.user.User;
import com.project.corpodash.domain.user.interfaces.UserRepository;

@Service
public class SignUpUseCase extends Usecase<Void, Void> {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SignUpUseCase(EntityManagerService ems, PasswordEncoder passwordEncoder, UserRepository userRepository) {
    super(ems);
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void execute(AuthDto.SignUpRequest request) {
    if (userRepository.findByEmail(Email.of(request.email)).isPresent()) {
      throw new EntityExistsException("Email in use");
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
