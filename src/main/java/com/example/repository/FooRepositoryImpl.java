package com.example.repository;

import static org.hibernate.jpa.QueryHints.HINT_READONLY;

import com.example.model.foo.FooModel;
import com.google.common.base.Preconditions;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class FooRepositoryImpl implements FooRepository {

  @PersistenceContext(name = "foo")
  private EntityManager entityManager;

  public FooRepositoryImpl(@CurrentSession EntityManager entityManager) {
    this.entityManager = Preconditions.checkNotNull(entityManager);
  }

  @Override
  @Transactional(readOnly = true)
  public List<FooModel> getFoos() {
    String queryStr = "SELECT * FROM foo";
    Query query = entityManager.createNativeQuery(queryStr, FooModel.class);
    query.setHint(HINT_READONLY, true);
    log.info("actual foo repository is used");
    return query.getResultList();
  }
}
