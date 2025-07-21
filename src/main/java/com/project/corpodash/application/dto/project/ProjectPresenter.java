package com.project.corpodash.application.dto.project;

import com.project.corpodash.application.dto.PresenterUtils;
import com.project.corpodash.application.dto.user.UserPresenter;
import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.project.projectstatus.ProjectStatus;

import java.util.List;

public record ProjectPresenter(
    Long id, String name, String description, ProjectStatus status, List<UserPresenter> team, UserPresenter creator) {
  public ProjectPresenter(Project project) {
    this(
        project.getId(),
        project.getName(),
        project.getDescription(),
        project.getStatus(),
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
