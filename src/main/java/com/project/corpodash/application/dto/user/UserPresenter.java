package com.project.corpodash.application.dto.user;

import com.project.corpodash.application.dto.PresenterUtils;
import com.project.corpodash.domain.user.User;
import java.util.List;

public record UserPresenter(Long id, String fullName, String firstName, String lastName, String email) {

  public UserPresenter(User user) {
    this(user.getId(), user.getName().getFullName(), user.getName().getFirstName(), user.getName().getLastName(),
        user.getEmail().getValue());
  }

  public static UserPresenter of(User user) {
    return new UserPresenter(user);
  }

  public static List<UserPresenter> ofMany(List<User> users) {
    return PresenterUtils.ofMany(users, UserPresenter::of);
  }
}
