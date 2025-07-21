package com.project.corpodash.application.dto.project;

import java.util.List;
import java.util.Optional;

public class ProjectDto {
  public static record CreateProjectDto(String name, Optional<List<Long>> team) {}

  public static record UpdateProjectDto(Long id, Optional<String> name, Optional<List<Long>> team) {
    public boolean allEmpty() {
      return name.isEmpty() && team.isEmpty();
    }
  }

  public static record AddTeamMemberDto(Long projectId, Long userId) {}

  public static record RemoveTeamMemberDto(Long projectId, Long userId) {}
}
