package com.project.corpodash.application.dto;

import java.util.List;

public abstract class PresenterUtils {

  public static <T, R> List<R> ofMany(List<T> entities, java.util.function.Function<T, R> mapper) {
    return entities.stream().map(mapper).toList();
  }
}
