package com.csc540.ups.service;

import com.csc540.ups.entity.Zone;

public interface ZoneService {

  void remove(String id);

  void insert(Zone zone);

  void select(String id);
}
