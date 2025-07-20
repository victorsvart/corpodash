package com.project.corpodash.application.usecase.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corpodash.application.dto.user.UserPresenter;
import com.project.corpodash.infrastructure.persistence.user.JpaUserRepository;

@Service
public class GetAllUsersUseCase {
  private final JpaUserRepository userRepository;

  public GetAllUsersUseCase(JpaUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserPresenter> execute() {
    return userRepository.findAll()
        .stream()
        .map(UserPresenter::new)
        .toList();
  }
}
