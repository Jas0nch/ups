package com.csc540.ups.service.impl;

import com.csc540.ups.dao.SpaceDao;
import com.csc540.ups.entity.Space;
import com.csc540.ups.service.SpaceService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements SpaceService {

  @Resource
  public SpaceDao spaceDao;

  @Override
  public void insert(Space space) {
    spaceDao.insert(space.getUuid(), space.getSpaceNum(), space.getSpaceType());
  }

  @Override
  public Space select(String id) {
    return spaceDao.select(id);
  }
}
