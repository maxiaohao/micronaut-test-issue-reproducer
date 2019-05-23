package com.example.singleton;

import com.example.model.bar.BarModel;
import com.example.model.foo.FooModel;
import com.example.repository.BarRepository;
import com.example.repository.FooRepository;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ExampleSingleton {

  @Inject
  FooRepository fooRepository;

  @Inject
  BarRepository barRepository;

  public ExampleSingleton(FooRepository fooRepository, BarRepository barRepository) {
    this.fooRepository = Preconditions.checkNotNull(fooRepository);
    this.barRepository = Preconditions.checkNotNull(barRepository);
  }

  public List<FooModel> getFooModels() {
    List<FooModel> fooModels = fooRepository.getFoos();
    // fooModels.stream()
    //     .map(foo -> BarModel.builder()
    //         .id(foo.getId())
    //         .name(foo.getName())
    //         .build()
    //     )
    //     .forEach(barRepository::save);
    return fooModels;
  }

}
