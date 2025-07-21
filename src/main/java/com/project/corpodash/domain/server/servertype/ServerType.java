package com.project.corpodash.domain.server.servertype;

public enum ServerType {
  WEB("web"),
  DB("db");

  private final String name;

  ServerType(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
