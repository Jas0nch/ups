package com.csc540.ups.service.impl;

import com.csc540.ups.dao.SpaceDao;
import com.csc540.ups.dao.ZoneDao;
import com.csc540.ups.entity.Space;
import com.csc540.ups.entity.Zone;
import com.csc540.ups.service.ZoneService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public List<Zone> selectAllByLotID(String lotID) {
    List<Zone> zones = zoneDao.selectAllByLotID(lotID);

    for (Zone z : zones) {
      z.setSpaces(spaceDao.selectAllByZoneID(z.getUuid()));
    }

    return zones;
  }

  @Transactional
  @Override
  public void insertAndUpdate(Zone z) {
    zoneDao.insert(z.getUuid(), z.getLotID(), z.getName(), z.getSpaceNum(), z.getStartNum());

    for (Space s : z.getSpaces()) {
      spaceDao.update(s.getUuid(), s.getZoneID());
    }
  }

  @Transactional
  @Override
  public void insert(Zone z) {
    zoneDao.insert(z.getUuid(), z.getLotID(), z.getName(), z.getSpaceNum(), z.getStartNum());
  }

  @Override
  public Zone select(String id) {
    Zone z = zoneDao.select(id);
    z.setSpaces(spaceDao.selectAllByZoneID(id));

    return z;
  }
}
