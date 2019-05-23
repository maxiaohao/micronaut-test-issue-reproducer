package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.model.foo.FooModel;
import com.example.repository.FooRepository;
import com.example.repository.FooRepositoryImpl;
import com.example.singleton.ExampleSingleton;
import com.google.common.collect.Lists;
import io.micronaut.context.annotation.Primary;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import java.util.List;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@MicronautTest(transactional = false, environments = "local")
@Slf4j
public class ExampleTest {

  @Inject
  private ExampleSingleton exampleSingleton;

  private static List<FooModel> mockFoos = Lists.newArrayList(
      FooModel.builder()
          .id(1L)
          .name("mock model name")
          .build()
  );

  @Primary
  @MockBean(FooRepositoryImpl.class)
  FooRepository mockFooRepository() {
    FooRepository mock = mock(FooRepository.class);
    when(mock.getFoos()).thenReturn(mockFoos);
    return mock;
  }

  @Test
  public void testFooToBar() {
    List<FooModel> fooModels = exampleSingleton.getFooModels();
    assertNotNull(fooModels);
    assertEquals(1, fooModels.size());
    assertEquals("mock model name", fooModels.get(0).getName());
    log.info("Getting model {}, mock foo repository is used.", fooModels.get(0));
  }

}
