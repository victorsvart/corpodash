package com.project.corpodash.domain.project;

import java.util.List;

import com.project.corpodash.domain.base.Auditable;
import com.project.corpodash.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity()
@Table(name = "projects")
public class Project extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(name = "project_team", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> team;

  @ManyToOne(optional = false)
  @JoinColumn(name = "creator_id", nullable = false)
  private User creator;

  protected Project() {
  }

  public Project(Builder builder) {
    this.name = builder.name;
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

  public boolean isCreator(Long userId) {
    return this.creator.getId().equals(userId);
  }

  public void updateName(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Project name can't be empty");
    }

    this.name = name;
  }

  public boolean isUserOnTeam(Long userId) {
    return this.getTeam().stream().anyMatch(member -> member.getId().equals(userId));
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<User> getTeam() {
    return team;
  }

  public User getCreator() {
    return creator;
  }

  public static class Builder {
    private String name;
    private List<User> team;
    private User creator;

    public Builder name(String name) {
      System.out.println("HELOOOOOOo" + name);
      this.name = name;
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
