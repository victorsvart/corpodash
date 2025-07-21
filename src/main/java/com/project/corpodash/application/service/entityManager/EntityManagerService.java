package com.project.corpodash.application.service.entityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EntityManagerService {

  @PersistenceContext private EntityManager entityManager;

  public <T, ID> T getReferenceOrThrow(Class<T> entityClass, ID id) {
    try {
      T entity = entityManager.find(entityClass, id);
      if (entity == null) {
        throw new EntityNotFoundException(
            entityClass.getSimpleName() + " with ID " + id + " not found.");
      }
      return entity;
    } catch (EntityNotFoundException e) {
      throw e;
    }
  }

  public <T, ID> Optional<T> getReferenceIfExists(Class<T> entityClass, ID id) {
    T entity = entityManager.find(entityClass, id);
    return Optional.ofNullable(entity);
  }

  public <T, ID> List<T> getReferences(Class<T> entityClass, List<ID> ids) {
    return ids.stream()
        .map(id -> getReferenceOrThrow(entityClass, id))
        .collect(Collectors.toList());
  }

  public <T, ID> List<Optional<T>> getReferencesIfExists(Class<T> entityClass, List<ID> ids) {
    return ids.stream()
        .map(id -> getReferenceIfExists(entityClass, id))
        .collect(Collectors.toList());
  }
}
