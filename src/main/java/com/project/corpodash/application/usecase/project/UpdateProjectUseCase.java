package com.project.corpodash.application.usecase.project;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corpodash.application.base.Usecase;
import com.project.corpodash.application.dto.project.ProjectDto;
import com.project.corpodash.application.service.entityManager.EntityManagerService;
import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.project.interfaces.ProjectRepository;
import com.project.corpodash.domain.user.User;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UpdateProjectUseCase extends Usecase<Project, ProjectDto.UpdateProjectDto> {
  private final ProjectRepository projectRepository;

  public UpdateProjectUseCase(EntityManagerService ems, ProjectRepository projectRepository) {
    super(ems);
    this.projectRepository = projectRepository;
  }

  private void checkIfNameInUse(Long id, String name) {
    if (projectRepository.existsByNameAndIdNot(name, id)) {
      throw new EntityExistsException("Project name already in use!");
    }
  }

  public Project execute(ProjectDto.UpdateProjectDto dto) {
    Project project = projectRepository.findById(dto.id())
        .orElseThrow(() -> new EntityNotFoundException("No project with provided id"));

    if (dto.allEmpty()) {
      return project;
    }

    checkIfNameInUse(dto.id(), dto.name().get());

    List<User> updatedTeam = ems.getReferences(User.class, dto.team().get());
    project.updateName(dto.name().get());
    project.updateTeam(updatedTeam);

    return projectRepository.save(project);
  }
}
