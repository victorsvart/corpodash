package com.project.corpodash.domain.base.valueobject;

import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.ArrayUtils;

@Embeddable
public class Name {
  private String name;

  protected Name() {}

  protected Name(String value) {
    this.name = value;
  }

  public String getFullName() {
    return this.name;
  }

  private String[] splitName() {
    if (this.name == null || this.name.trim().isEmpty()) {
      return ArrayUtils.EMPTY_STRING_ARRAY;
    }

    return this.name.split("\\s+");
  }

  public String getFirstName() {
    String[] names = splitName();
    return names[0];
  }

  public String getLastName() {
    String[] names = splitName();
    return names[names.length - 1];
  }

  public void update(String value) {
    if (this.name.isEmpty()) {
      throw new RuntimeException("Can't update non existing email instance");
    }

    Name.validate(value);
    this.name = value;
  }

  public static void validate(String value) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("Value cannot be empty");
    }
  }

  public static Name of(String value) {
    return new Builder().value(value).build();
  }

  public static class Builder {
    private String name;

    public Builder value(String value) throws IllegalArgumentException {
      Name.validate(value);
      this.name = value;
      return this;
    }

    public Name build() {
      return new Name(this.name);
    }
  }
}
