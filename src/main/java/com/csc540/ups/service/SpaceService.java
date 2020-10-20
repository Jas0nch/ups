package com.csc540.ups.service;

import com.csc540.ups.entity.Space;
import com.csc540.ups.enums.SpaceType;
import java.util.List;

public interface SpaceService {

  void insert(Space space);

  Space select(String id);

  List<Space> selectAllByZoneID(String zoneID);

  void update(Space space);

  void updateType(String id, SpaceType spaceType);
}
