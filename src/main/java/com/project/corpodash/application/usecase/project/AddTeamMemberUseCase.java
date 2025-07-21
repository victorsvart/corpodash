package com.project.corpodash.application.usecase.project;

import org.springframework.stereotype.Service;
import com.project.corpodash.application.base.Usecase;
import com.project.corpodash.application.dto.project.ProjectDto;
import com.project.corpodash.application.dto.project.ProjectDto.AddTeamMemberDto;
import com.project.corpodash.application.service.entityManager.EntityManagerService;
import com.project.corpodash.application.session.Session;
import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.project.interfaces.ProjectRepository;
import com.project.corpodash.domain.user.User;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AddTeamMemberUseCase extends Usecase<Project, ProjectDto.AddTeamMemberDto> {
  private ProjectRepository projectRepository;

  public AddTeamMemberUseCase(EntityManagerService ems, ProjectRepository projectRepository) {
    super(ems);
    this.projectRepository = projectRepository;
  }

  public Project execute(AddTeamMemberDto dto) {
    Project project = projectRepository.findById(dto.projectId())
        .orElseThrow(() -> new EntityNotFoundException("Cant find specified project"));

    if (!project.isCreator(Session.getUserId())) {
      throw new RuntimeException("Can't update team which you are not the creator");
    }

    User user = ems.getReferenceOrThrow(User.class, dto.userId());
    if (project.isUserOnTeam(user.getId())) {
      throw new EntityExistsException("User already on project's team");
    }

    project.addTeamMember(user);
    return projectRepository.save(project);
  }
}
