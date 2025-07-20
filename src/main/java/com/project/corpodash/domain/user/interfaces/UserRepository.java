package com.project.corpodash.domain.user.interfaces;

import java.util.List;
import java.util.Optional;

import com.project.corpodash.domain.email.Email;
import com.project.corpodash.domain.user.User;

public interface UserRepository {
  Optional<User> findByEmail(Email email);

  Optional<User> findById(Long id);

  List<User> findAll();

  User save(User user);
}
