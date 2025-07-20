package com.project.corpodash.application.controller.project;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.corpodash.application.dto.project.ProjectDto;
import com.project.corpodash.application.dto.project.ProjectPresenter;
import com.project.corpodash.application.usecase.project.CreateProjectUseCase;
import com.project.corpodash.application.usecase.project.UpdateProjectUseCase;
import com.project.corpodash.domain.project.Project;

@RestController
@RequestMapping("/project")
public class ProjectController {
  private final CreateProjectUseCase createProjectUseCase;
  private final UpdateProjectUseCase updateProjectUseCase;

  public ProjectController(CreateProjectUseCase createProjectUseCase, UpdateProjectUseCase updateProjectUseCase) {
    this.createProjectUseCase = createProjectUseCase;
    this.updateProjectUseCase = updateProjectUseCase;
  }

  @PostMapping("/createProject")
  @PreAuthorize("haRole('USER')")
  public ResponseEntity<ProjectPresenter> createProject(@RequestBody ProjectDto.CreateProjectDto dto) {
    Project project = createProjectUseCase.execute(dto);
    return ResponseEntity.ok(ProjectPresenter.of(project));
  }

  @PutMapping("/updateProject")
  @PreAuthorize("haRole('USER')")
  public ResponseEntity<ProjectPresenter> updateProject(@RequestBody ProjectDto.UpdateProjectDto dto) {
    Project project = updateProjectUseCase.execute(dto);
    return ResponseEntity.ok(ProjectPresenter.of(project));
  }
}
