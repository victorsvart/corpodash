package com.project.corpodash.domain.project.projectstatus;

public enum ProjectStatus {
  ACTIVE("ACTIVE"),
  INACTIVE("INACTIVE");

  private final String status;

  ProjectStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
