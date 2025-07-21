package com.project.corpodash.infrastructure.persistence.project;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.project.interfaces.ProjectRepository;

public interface JpaProjectRepository extends JpaRepositoryImplementation<Project, Long>, ProjectRepository {
}
