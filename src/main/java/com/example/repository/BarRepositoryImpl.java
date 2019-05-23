package com.example.repository;

import static org.hibernate.jpa.QueryHints.HINT_READONLY;

import com.example.model.bar.BarModel;
import com.google.common.base.Preconditions;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
public class BarRepositoryImpl implements BarRepository {

  @PersistenceContext(name = "bar")
  private EntityManager entityManager;

  public BarRepositoryImpl(@CurrentSession EntityManager entityManager) {
    this.entityManager = Preconditions.checkNotNull(entityManager);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BarModel> getBars() {
    String queryStr = "SELECT * FROM bar";
    Query query = entityManager.createNativeQuery(queryStr, BarModel.class);
    query.setHint(HINT_READONLY, true);
    return query.getResultList();
  }

  @Override
  @Transactional
  public BarModel save(BarModel model) {
    entityManager.persist(model);
    return model;
  }
}
