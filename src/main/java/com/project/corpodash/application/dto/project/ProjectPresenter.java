package com.project.corpodash.application.dto.project;

import java.util.List;

import com.project.corpodash.application.dto.PresenterUtils;
import com.project.corpodash.application.dto.user.UserPresenter;
import com.project.corpodash.domain.project.Project;

public record ProjectPresenter(Long id, String name, List<UserPresenter> team, UserPresenter creator) {
  public ProjectPresenter(Project project) {
    this(
        project.getId(),
        project.getName(),
        UserPresenter.ofMany(project.getTeam()),
        UserPresenter.of(project.getCreator()));
  }

  public static ProjectPresenter of(Project project) {
    return new ProjectPresenter(project);
  }

  public static List<ProjectPresenter> ofMany(List<Project> projects) {
    return PresenterUtils.ofMany(projects, ProjectPresenter::of);
  }
}
