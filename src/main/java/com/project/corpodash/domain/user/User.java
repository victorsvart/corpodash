package com.project.corpodash.domain.user;

import java.util.List;
import java.util.Set;

import com.project.corpodash.domain.base.Auditable;
import com.project.corpodash.domain.email.Email;
import com.project.corpodash.domain.name.Name;
import com.project.corpodash.domain.project.Project;
import com.project.corpodash.domain.role.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity()
@Table(name = "users")
public class User extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Name name;
  private Email email;
  private String password;
  private boolean isAccountNonExpired = true;
  private boolean isAccountNonLocked = true;
  private boolean isCredentialsNonExpired = true;
  private boolean isEnabled = true;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Set<Role> roles;

  @ManyToMany(mappedBy = "team")
  private List<Project> projects;

  @OneToMany(mappedBy = "creator")
  private List<Project> creatorOf;

  protected User() {
  }

  protected User(Builder builder) {
    this.name = builder.name;
    this.email = builder.email;
    this.password = builder.password;
    this.roles = builder.roles;
  }

  public Long getId() {
    return this.id;
  }

  public Name getName() {
    return name;
  }

  public Email getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public boolean isAccountNonExpired() {
    return isAccountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return isAccountNonLocked;
  }

  public boolean isCredentialsNonExpired() {
    return isCredentialsNonExpired;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    User user = (User) o;
    return id != null && id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  public static class Builder {
    private Name name;
    private Email email;
    private String password;
    private Set<Role> roles;

    public Builder name(String name) {
      this.name = Name.of(name);
      return this;
    }

    public Builder email(String email) {
      this.email = Email.of(email);
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder roles(Set<Role> roles) {
      this.roles = roles;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
