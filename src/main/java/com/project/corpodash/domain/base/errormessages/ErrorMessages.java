package com.project.corpodash.domain.base.errormessages;

public class ErrorMessages {
  private ErrorMessages() {
  }

  public static final String COMMON_EMPTY = "Can't be empty";

  public static final String URL_EMPTY = "URL " + COMMON_EMPTY;
  public static final String BRANCH_EMPTY = "Branch " + COMMON_EMPTY;
}
