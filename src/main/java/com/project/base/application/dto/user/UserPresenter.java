package com.project.base.application.dto.user;

import com.project.base.domain.user.User;

public record UserPresenter(String fullName, String email) {
  public UserPresenter(User user) {
    this(user.getName().getFullName(), user.getEmail().getValue());
  }
}
