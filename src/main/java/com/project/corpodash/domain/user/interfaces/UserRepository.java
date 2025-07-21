package com.project.corpodash.domain.user.interfaces;

import com.project.corpodash.domain.base.valueobject.Email;
import com.project.corpodash.domain.user.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
  Optional<User> findByEmail(Email email);

  Optional<User> findById(Long id);

  List<User> findAll();

  User save(User user);
}
