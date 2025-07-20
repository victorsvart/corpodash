package com.project.corpodash.application.base;

import com.project.corpodash.application.service.entityManager.EntityManagerService;

public abstract class Usecase<T, X> {

  protected final EntityManagerService ems;

  public Usecase(EntityManagerService ems) {
    this.ems = ems;
  }

  public abstract T execute(X dto);
}
