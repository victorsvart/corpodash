package com.project.corpodash.domain.plan;

import java.time.Instant;

import com.project.corpodash.domain.base.Auditable;
import com.project.corpodash.domain.plan.planenv.PlanEnv;
import com.project.corpodash.domain.plan.planstatus.PlanStatus;
import com.project.corpodash.domain.project.Project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity()
@Table(name = "plans")
public class Plan extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String url;
  private Instant lastDeploy;

  @Enumerated(EnumType.STRING)
  @Column(name = "environment")
  private PlanEnv environment;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private PlanStatus status;

  @ManyToOne(optional = false)
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  protected Plan() {
  }

  public Plan(Builder builder) {
    this.url = builder.url;
    this.lastDeploy = builder.lastDeploy;
    this.environment = builder.environment;
    this.status = builder.status;
  }

  public Long getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public Instant getLastDeploy() {
    return lastDeploy;
  }

  public PlanEnv getEnvironment() {
    return environment;
  }

  public PlanStatus getStatus() {
    return status;
  }

  public static class Builder {
    private String url;
    private Instant lastDeploy;
    private PlanEnv environment;
    private PlanStatus status;

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder lastDeploy(Instant lastDeploy) {
      this.lastDeploy = lastDeploy;
      return this;
    }

    public Builder environment(PlanEnv environment) {
      this.environment = environment;
      return this;
    }

    public Builder status(PlanStatus status) {
      this.status = status;
      return this;
    }

    public Plan build() {
      return new Plan(this);
    }
  }
}
