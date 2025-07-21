package com.project.corpodash.domain.plan.planstatus;

public enum PlanStatus {
  HEALTHY("healthy"),
  UNHEALTHY("unhealthy"),
  DEPLOY("deploying");

  private final String name;

  PlanStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
