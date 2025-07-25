package com.project.corpodash.infrastructure.persistence.project;

import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.project.interfaces.ProjectRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface JpaProjectRepository
    extends JpaRepositoryImplementation<Project, Long>, ProjectRepository {}
