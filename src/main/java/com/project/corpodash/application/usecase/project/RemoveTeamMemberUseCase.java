package com.project.corpodash.application.usecase.project;

import org.springframework.stereotype.Service;

import com.project.corpodash.application.base.Usecase;
import com.project.corpodash.application.dto.project.ProjectDto;
import com.project.corpodash.application.dto.project.ProjectDto.RemoveTeamMemberDto;
import com.project.corpodash.application.service.entityManager.EntityManagerService;
import com.project.corpodash.application.session.Session;
import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.project.interfaces.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RemoveTeamMemberUseCase extends Usecase<Project, ProjectDto.RemoveTeamMemberDto> {
  private final ProjectRepository projectRepository;

  public RemoveTeamMemberUseCase(EntityManagerService ems, ProjectRepository projectRepository) {
    super(ems);
    this.projectRepository = projectRepository;
  }

  @Override
  public Project execute(RemoveTeamMemberDto dto) {
    Project project = projectRepository.findById(dto.projectId())
        .orElseThrow(() -> new EntityNotFoundException("Cant find specified project"));

    if (!project.isCreator(Session.getUserId())) {
      throw new RuntimeException("Can't update team which you are not the creator");
    }

    if (!project.isUserOnTeam(dto.userId())) {
      throw new RuntimeException("User is not on the team");
    }

    project.removeTeamMember(dto.userId());
    return projectRepository.save(project);
  }

}
