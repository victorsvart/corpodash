package com.project.corpodash.domain.project.interfaces;

import com.project.corpodash.domain.project.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
  Optional<Project> findByName(String name);

  Optional<Project> findById(Long id);

  boolean existsByNameAndIdNot(String name, Long id);

  List<Project> findAll();

  List<Project> findAllByCreatorId(Long id);

  Project save(Project project);
}
