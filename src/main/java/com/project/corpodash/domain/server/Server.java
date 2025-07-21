package com.project.corpodash.domain.server;

import com.project.corpodash.domain.base.Auditable;
import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.server.serverenv.ServerEnv;
import com.project.corpodash.domain.server.serverstatus.ServerStatus;
import com.project.corpodash.domain.server.servertype.ServerType;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servers")
public class Server extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private int cores;
  private int memory;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ServerStatus status;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ServerEnv environment;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ServerType type;

  @ManyToMany(mappedBy = "servers")
  private List<Project> projects = new ArrayList<>();

  protected Server() {
  }

  public Server(Builder builder) {
    this.name = builder.name;
    this.cores = builder.cores;
    this.memory = builder.memory;
    this.status = builder.status;
    this.environment = builder.environment;
    this.type = builder.type;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getCores() {
    return cores;
  }

  public int getMemory() {
    return memory;
  }

  public ServerStatus getStatus() {
    return status;
  }

  public ServerEnv getEnvironment() {
    return environment;
  }

  public ServerType getType() {
    return type;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public static class Builder {
    private String name;
    private int cores;
    private int memory;
    private ServerStatus status;
    private ServerType type;
    private ServerEnv environment;

    public Builder cores(int cores) {
      this.cores = cores;
      return this;
    }

    public Builder memory(int memory) {
      this.memory = memory;
      return this;
    }

    public Builder status(ServerStatus status) {
      this.status = status;
      return this;
    }

    public Builder type(ServerType type) {
      this.type = type;
      return this;
    }

    public Builder environment(ServerEnv env) {
      this.environment = env;
      return this;
    }

    public Server build(int projectServerCount) {
      this.name = String.format("%s-%s-%d", this.environment.name().toLowerCase(), this.type.name().toLowerCase(),
          projectServerCount);
      return new Server(this);
    }
  }
}
