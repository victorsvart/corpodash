package com.project.corpodash.domain.plan.planenv;

public enum PlanEnv {
  PROD("prod"),
  DEV("dev"),
  STAGING("stag");

  private final String name;

  PlanEnv(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
