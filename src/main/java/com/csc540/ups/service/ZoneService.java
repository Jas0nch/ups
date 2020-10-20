package com.csc540.ups.service;

import com.csc540.ups.entity.Zone;
import java.util.List;

public interface ZoneService {

  void insert(Zone zone);

  void insertAndUpdate(Zone zone);

  Zone select(String id);

  void remove(String id);

  List<Zone> selectAllByLotID(String lotID);
}
