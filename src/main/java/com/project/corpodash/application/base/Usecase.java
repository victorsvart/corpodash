package com.project.corpodash.application.base;

import com.project.corpodash.application.service.entityManager.EntityManagerService;

public abstract class Usecase<T, X> {

  protected final EntityManagerService ems;

  public Usecase(EntityManagerService ems) {
    this.ems = ems;
  }

  public T execute() {
    throw new UnsupportedOperationException("Usecase requires input parameter");
  }

  public T execute(X dto) {
    throw new UnsupportedOperationException("Usecase does not support input parameter");
  }
}
