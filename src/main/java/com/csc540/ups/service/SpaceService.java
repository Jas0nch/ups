package com.csc540.ups.service;

import com.csc540.ups.entity.Space;

public interface SpaceService {

  void insert(Space space);

  Space select(String id);
}
