package com.csc540.ups.service.impl;

import com.csc540.ups.dao.SpaceDao;
import com.csc540.ups.dao.ZoneDao;
import com.csc540.ups.entity.Zone;
import com.csc540.ups.service.ZoneService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ZoneServiceImpl implements ZoneService {

  @Resource
  ZoneDao zoneDao;

  @Resource
  SpaceDao spaceDao;

  @Override
  public void remove(String id) {
    zoneDao.remove(id);
  }

  @Override
  public void insert(Zone zone) {

  }

  @Override
  public void select(String id) {

  }
}
