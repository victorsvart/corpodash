package com.project.corpodash.domain.server.serverenv;

public enum ServerEnv {
  PROD("prod"),
  DEV("dev"),
  STAGING("stag");

  private final String name;

  ServerEnv(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
