package com.project.corpodash.domain.base.helpers;

public class StringHelpers {
  private StringHelpers() {
  }

  public static void validate(String value, String message) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }
}
