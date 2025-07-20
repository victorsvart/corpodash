package com.project.base.domain.email;

import jakarta.persistence.Embeddable;

@Embeddable
public class Email {
  private String email;

  protected Email() {
  }

  protected Email(String value) {
    this.email = value;
  }

  public String getValue() {
    return this.email;
  }

  public void update(String value) {
    if (this.email.isEmpty()) {
      throw new RuntimeException("Can't update non existing email instance");
    }

    Email.validate(value);
    this.email = value;
  }

  public static void validate(String value) {
    if (!value.isEmpty()) {
      return;
    }

    throw new IllegalArgumentException("Email can't be empty");
  }

  public static Email of(String value) {
    return new Builder().value(value).build();
  }

  public static class Builder {
    private String email;

    public Builder value(String value) throws IllegalArgumentException {
      Email.validate(value);
      this.email = value;
      return this;
    }

    public Email build() {
      return new Email(this.email);
    }
  }
}
