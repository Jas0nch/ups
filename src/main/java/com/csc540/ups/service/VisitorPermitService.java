package com.csc540.ups.service;

import com.csc540.ups.entity.VisitorPermit;
import com.csc540.ups.enums.SpaceType;

public interface VisitorPermitService {

  VisitorPermit getPermit(int spaceNum,
      String lotName,
      int duration,
      String identifier,
      SpaceType spaceType,
      String carNum,
      String year,
      String model,
      String manufacturer,
      String color,
      String licensePlate,
      String phone);

  VisitorPermit search(String identifier);

  String getLotName(String identifier);

  boolean ExitLot(VisitorPermit permit);

  VisitorPermit selectByCarNum(String carNum);
}
