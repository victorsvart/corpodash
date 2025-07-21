package com.project.corpodash.domain.projectrepo.interfaces;

import java.util.Optional;

import com.project.corpodash.domain.projectrepo.ProjectRepo;

public interface ProjectRepoRepository {
  Optional<ProjectRepo> findByUrl(String url);
}
