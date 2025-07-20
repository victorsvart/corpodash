package com.project.corpodash.infrastructure.persistence.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.corpodash.domain.email.Email;
import com.project.corpodash.domain.user.User;
import com.project.corpodash.domain.user.interfaces.UserRepository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
  Optional<User> findByEmail(Email email);

  boolean existsByNameAndIdNot(String name, Long id);
}
