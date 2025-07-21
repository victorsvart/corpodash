package com.project.corpodash.application.usecase.user;

import com.project.corpodash.application.base.Usecase;
import com.project.corpodash.application.service.entityManager.EntityManagerService;
import com.project.corpodash.domain.user.User;
import com.project.corpodash.domain.user.interfaces.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetAllUsersUseCase extends Usecase<List<User>, Void> {
  private final UserRepository userRepository;

  public GetAllUsersUseCase(EntityManagerService ems, UserRepository userRepository) {
    super(ems);
    this.userRepository = userRepository;
  }

  public List<User> execute() {
    return userRepository.findAll();
  }
}
