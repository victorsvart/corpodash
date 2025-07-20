package com.project.corpodash.domain.project.interfaces;

import java.util.List;
import java.util.Optional;

import com.project.corpodash.domain.project.Project;

public interface ProjectRepository {
  Optional<Project> findByName(String name);

  Optional<Project> findById(Long id);

  boolean existsByNameAndIdNot(String name, Long id);

  List<Project> findAll();

  Project save(Project project);
}
