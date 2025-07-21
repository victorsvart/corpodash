package com.project.corpodash.domain.project;

import com.project.corpodash.domain.base.Auditable;
import com.project.corpodash.domain.plan.Plan;
import com.project.corpodash.domain.project.projectstatus.ProjectStatus;
import com.project.corpodash.domain.projectrepo.ProjectRepo;
import com.project.corpodash.domain.server.Server;
import com.project.corpodash.domain.user.User;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProjectStatus status;

  @ManyToMany
  @JoinTable(name = "project_team", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> team = new ArrayList<>();

  @ManyToOne(optional = false)
  @JoinColumn(name = "creator_id", nullable = false)
  private User creator;

  @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
  private ProjectRepo repository;

  @ManyToMany
  @JoinTable(name = "project_server", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "server_id"))
  private List<Server> servers = new ArrayList<>();

  @OneToMany(mappedBy = "project")
  private List<Plan> plans = new ArrayList<>();

  protected Project() {
  }

  public Project(Builder builder) {
    this.name = builder.name;
    this.description = builder.description;
    this.status = builder.status;
    this.team = builder.team;
    this.creator = builder.creator;
  }

  public void addTeamMember(User user) {
    this.team.add(user);
  }

  public void removeTeamMember(Long userId) {
    this.team.removeIf(member -> member.getId().equals(userId));
  }

  public void updateTeam(List<User> team) {
    this.team = team;
  }

  public void updateDescription(String description) {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Description can't be empty");
    }
    this.description = description;
  }

  public void updateName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Project name can't be empty");
    }
    this.name = name;
  }

  public void updateStatus(ProjectStatus status) {
    this.status = status;
  }

  public void updateRepository(ProjectRepo repo) {
    if (this.repository != null && this.repository.getId().equals(repo.getId())) {
      throw new IllegalArgumentException("Repository is already the one specified");
    }
    this.repository = repo;
  }

  public boolean isUserOnTeam(Long userId) {
    return this.team.stream().anyMatch(member -> member.getId().equals(userId));
  }

  public boolean isCreator(Long userId) {
    return this.creator.getId().equals(userId);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public ProjectStatus getStatus() {
    return status;
  }

  public List<User> getTeam() {
    return team;
  }

  public User getCreator() {
    return creator;
  }

  public ProjectRepo getRepository() {
    return repository;
  }

  public List<Server> getServers() {
    return servers;
  }

  public List<Plan> getPlans() {
    return plans;
  }

  public static class Builder {
    private String name;
    private String description;
    private ProjectStatus status;
    private List<User> team = new ArrayList<>();
    private User creator;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder status(ProjectStatus status) {
      this.status = status;
      return this;
    }

    public Builder team(List<User> team) {
      this.team = team;
      return this;
    }

    public Builder creator(User creator) {
      this.creator = creator;
      return this;
    }

    public Project build() {
      return new Project(this);
    }
  }
}
