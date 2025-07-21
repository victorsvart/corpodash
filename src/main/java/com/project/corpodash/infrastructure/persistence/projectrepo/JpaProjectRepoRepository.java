package com.project.corpodash.infrastructure.persistence.projectrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.corpodash.domain.projectrepo.ProjectRepo;
import com.project.corpodash.domain.projectrepo.interfaces.ProjectRepoRepository;

public interface JpaProjectRepoRepository extends JpaRepository<ProjectRepo, Long>, ProjectRepoRepository {

}
