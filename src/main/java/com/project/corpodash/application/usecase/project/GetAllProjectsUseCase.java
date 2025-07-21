package com.project.corpodash.application.usecase.project;

import com.project.corpodash.application.base.Usecase;
import com.project.corpodash.application.service.entityManager.EntityManagerService;
import com.project.corpodash.application.session.Session;
import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.project.interfaces.ProjectRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetAllProjectsUseCase extends Usecase<List<Project>, Void> {
  private final ProjectRepository projectRepository;

  public GetAllProjectsUseCase(EntityManagerService ems, ProjectRepository projectRepository) {
    super(ems);
    this.projectRepository = projectRepository;
  }

  public List<Project> execute() {
    List<Project> projects = new ArrayList<>();
    if (Session.isAdmin()) {
      projects = projectRepository.findAll();
      return projects;
    }

    return projectRepository.findAllByCreatorId(Session.getUserId());
  }
}
