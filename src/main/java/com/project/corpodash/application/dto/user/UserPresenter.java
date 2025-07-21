package com.project.corpodash.application.dto.user;

import java.util.List;

import com.project.corpodash.application.dto.PresenterUtils;
import com.project.corpodash.domain.user.User;

public record UserPresenter(Long id, String fullName, String email) {

  public UserPresenter(User user) {
    this(user.getId(), user.getName().getFullName(), user.getEmail().getValue());
  }

  public static UserPresenter of(User user) {
    return new UserPresenter(user);
  }

  public static List<UserPresenter> ofMany(List<User> users) {
    return PresenterUtils.ofMany(users, UserPresenter::of);
  }

}
