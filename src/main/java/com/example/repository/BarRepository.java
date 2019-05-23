package com.example.repository;

import com.example.model.bar.BarModel;
import java.util.List;

public interface BarRepository {

  List<BarModel> getBars();

  BarModel save(BarModel model);

}
