package com.csc540.ups.service;

import com.csc540.ups.dao.SpaceDao;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {

  @Resource
  public SpaceDao spaceDao;
}
