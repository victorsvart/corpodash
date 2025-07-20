package com.project.base.application.usecase.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.base.application.dto.user.UserPresenter;
import com.project.base.infrastructure.persistence.user.UserRepository;

@Service
public class GetAllUsersUseCase {
  private final UserRepository userRepository;

  public GetAllUsersUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserPresenter> execute() {
    return userRepository.findAll()
        .stream()
        .map(UserPresenter::new)
        .toList();
  }
}
