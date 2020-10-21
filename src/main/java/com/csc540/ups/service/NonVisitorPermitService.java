package com.csc540.ups.service;

import com.csc540.ups.entity.NonVisitorPermit;
import com.csc540.ups.entity.Vehicle;
import com.csc540.ups.enums.PermitType;
import com.csc540.ups.enums.SpaceType;

public interface NonVisitorPermitService {

  NonVisitorPermit assignPermit(
      String univid,
      PermitType permitType,
      String identifier,
      SpaceType spaceType,
      String carNum,
      String year,
      String model,
      String manufacturer,
      String color,
      String licensePlate);

  NonVisitorPermit getPermitByUnivID(String univid);

  NonVisitorPermit getPermitByIdentifier(String identifier);

  void changeVehicle(String identifier, String univid, Vehicle vehicle, int i);

  NonVisitorPermit findPermitByCarNum(String carNum);

  boolean checkValidParking(String identifier);


}
