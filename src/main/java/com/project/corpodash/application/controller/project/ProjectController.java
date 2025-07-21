package com.project.corpodash.application.controller.project;

import com.project.corpodash.application.dto.project.ProjectDto;
import com.project.corpodash.application.dto.project.ProjectPresenter;
import com.project.corpodash.application.usecase.project.AddTeamMemberUseCase;
import com.project.corpodash.application.usecase.project.CreateProjectUseCase;
import com.project.corpodash.application.usecase.project.GetAllProjectsUseCase;
import com.project.corpodash.application.usecase.project.RemoveTeamMemberUseCase;
import com.project.corpodash.application.usecase.project.UpdateProjectUseCase;
import com.project.corpodash.domain.project.Project;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

  private final GetAllProjectsUseCase getAllProjectsUseCase;
  private final CreateProjectUseCase createProjectUseCase;
  private final UpdateProjectUseCase updateProjectUseCase;
  private final AddTeamMemberUseCase addTeamMemberUseCase;
  private final RemoveTeamMemberUseCase removeTeamMemberUseCase;

  public ProjectController(
      CreateProjectUseCase createProjectUseCase,
      UpdateProjectUseCase updateProjectUseCase,
      AddTeamMemberUseCase addTeamMemberUseCase,
      RemoveTeamMemberUseCase removeTeamMemberUseCase,
      GetAllProjectsUseCase getAllProjectsUseCase) {
    this.getAllProjectsUseCase = getAllProjectsUseCase;
    this.createProjectUseCase = createProjectUseCase;
    this.updateProjectUseCase = updateProjectUseCase;
    this.addTeamMemberUseCase = addTeamMemberUseCase;
    this.removeTeamMemberUseCase = removeTeamMemberUseCase;
  }

  @GetMapping("/getAllProjects")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<List<ProjectPresenter>> getAllProjects() {
    List<Project> projects = getAllProjectsUseCase.execute();
    return ResponseEntity.ok(ProjectPresenter.ofMany(projects));
  }

  @PostMapping("/addTeamMember")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ProjectPresenter> addTeamMember(
      @RequestBody ProjectDto.AddTeamMemberDto dto) {
    Project project = addTeamMemberUseCase.execute(dto);
    return ResponseEntity.ok(ProjectPresenter.of(project));
  }

  @PostMapping("/removeTeamMember")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ProjectPresenter> removeTeamMember(
      @RequestBody ProjectDto.RemoveTeamMemberDto dto) {
    Project project = removeTeamMemberUseCase.execute(dto);
    return ResponseEntity.ok(ProjectPresenter.of(project));
  }

  @PostMapping("/createProject")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ProjectPresenter> createProject(
      @RequestBody ProjectDto.CreateProjectDto dto) {
    Project project = createProjectUseCase.execute(dto);
    return ResponseEntity.ok(ProjectPresenter.of(project));
  }

  @PutMapping("/updateProject")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ProjectPresenter> updateProject(
      @RequestBody ProjectDto.UpdateProjectDto dto) {
    Project project = updateProjectUseCase.execute(dto);
    return ResponseEntity.ok(ProjectPresenter.of(project));
  }
}
