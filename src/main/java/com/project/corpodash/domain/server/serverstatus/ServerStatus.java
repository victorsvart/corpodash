package com.project.corpodash.domain.server.serverstatus;

public enum ServerStatus {
  ONLINE("online"),
  OFFLINE("offline");

  private final String name;

  ServerStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
