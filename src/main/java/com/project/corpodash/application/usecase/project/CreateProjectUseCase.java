package com.project.corpodash.application.usecase.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corpodash.application.base.Usecase;
import com.project.corpodash.application.dto.project.ProjectDto;
import com.project.corpodash.application.service.entityManager.EntityManagerService;
import com.project.corpodash.application.session.Session;
import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.project.interfaces.ProjectRepository;
import com.project.corpodash.domain.user.User;

@Service
public class CreateProjectUseCase extends Usecase<Project, ProjectDto.CreateProjectDto> {
  private final ProjectRepository projectRepository;

  public CreateProjectUseCase(EntityManagerService ems, ProjectRepository projectRepository) {
    super(ems);
    this.projectRepository = projectRepository;
  }

  public Project execute(ProjectDto.CreateProjectDto dto) {
    if (projectRepository.findByName(dto.name()).isPresent()) {
      throw new IllegalArgumentException("Project with given name already exists!");
    }

    System.out.println("USECASE" + dto.name());

    List<User> team = new ArrayList<>();
    dto.team().ifPresent(teamIds -> team.addAll(ems.getReferences(User.class, teamIds)));

    User creator = Session.getUser();
    Project project = new Project.Builder()
        .name(dto.name())
        .team(team)
        .creator(creator)
        .build();

    return projectRepository.save(project);
  }
}
