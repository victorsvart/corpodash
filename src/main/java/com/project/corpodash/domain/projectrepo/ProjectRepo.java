package com.project.corpodash.domain.projectrepo;

import java.time.Instant;
import com.project.corpodash.domain.base.Auditable;
import com.project.corpodash.domain.base.errormessages.ErrorMessages;
import com.project.corpodash.domain.base.helpers.StringHelpers;
import com.project.corpodash.domain.project.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity()
@Table(name = "project_repos")
public class ProjectRepo extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String url;
  private String branch;
  private Instant lastCommit;

  @OneToOne
  @JoinColumn(name = "project_id", nullable = false, unique = true)
  private Project project;

  protected ProjectRepo() {
  }

  public ProjectRepo(Builder builder) {
    this.url = builder.url;
    this.branch = builder.branch;
    this.lastCommit = builder.lastCommit;
  }

  public Long getId() {
    return this.id;
  }

  public String getUrl() {
    return url;
  }

  public String getBranch() {
    return branch;
  }

  public Instant getLastCommit() {
    return lastCommit;
  }

  public Project getProject() {
    return this.project;
  }

  public void updateUrl(String url) {
    StringHelpers.validate(url, ErrorMessages.URL_EMPTY);
    this.url = url;
  }

  public void updateBranch(String branch) {
    StringHelpers.validate(branch, ErrorMessages.BRANCH_EMPTY);
    this.branch = branch;
  }

  public void updateProject(Project project) {
    if (project.getId().equals(this.project.getId())) {
      throw new IllegalArgumentException("Project is already the one specified");
    }

    this.project = project;
  }

  public static class Builder {
    private String url;
    private String branch;
    private Instant lastCommit;

    public Builder url(String url) {
      StringHelpers.validate(url, ErrorMessages.URL_EMPTY);
      this.url = url;
      return this;
    }

    public Builder branch(String branch) {
      StringHelpers.validate(branch, ErrorMessages.BRANCH_EMPTY);
      this.branch = branch;
      return this;
    }

    public Builder lastCommit(Instant lastCommit) {
      this.lastCommit = lastCommit;
      return this;
    }
  }
}
