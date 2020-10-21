package com.csc540.ups.service.impl;

import com.csc540.ups.dao.SpaceDao;
import com.csc540.ups.entity.Space;
import com.csc540.ups.enums.SpaceStatus;
import com.csc540.ups.enums.SpaceType;
import com.csc540.ups.service.SpaceService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements SpaceService {

  @Resource
  public SpaceDao spaceDao;

  @Override
  public void insert(Space space) {
    spaceDao.insert(space.getUuid(), space.getSpaceNum(), space.getSpaceType(), space.getZoneID(),
        space.getSpaceStatus());
  }

  @Override
  public Space select(String id) {
    return spaceDao.select(id);
  }

  @Override
  public List<Space> selectAllByZoneID(String zoneID) {
    return spaceDao.selectAllByZoneID(zoneID);
  }

  @Override
  public void update(Space space) {
    spaceDao.update(space.getUuid(), space.getZoneID());
  }

  @Override
  public void updateType(String id, SpaceType spaceType) {
    spaceDao.updateType(id, spaceType);
  }

  @Override
  public void updateStatus(String id, SpaceStatus status) {
    spaceDao.updateStatus(id, status);
  }

}
