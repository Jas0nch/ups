package com.csc540.ups.service.impl;

import com.csc540.ups.dao.NvpermitDao;
import com.csc540.ups.dao.VehicleDao;
import com.csc540.ups.entity.NonVisitorPermit;
import com.csc540.ups.entity.Vehicle;
import com.csc540.ups.enums.PermitType;
import com.csc540.ups.enums.SpaceType;
import com.csc540.ups.service.NonVisitorPermitService;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class NonVisitorPermitServiceImpl implements NonVisitorPermitService {

  @Resource
  NvpermitDao nvpermitDao;

  @Resource
  VehicleDao vehicleDao;

  @Override
  public NonVisitorPermit assignPermit(
      String univid,
      PermitType permitType,
      String identifier,
      SpaceType spaceType,
      String carNum,
      String year,
      String model,
      String manufacturer,
      String color,
      String licensePlate) {

    NonVisitorPermit nonVisitorPermit =
        new NonVisitorPermit(
            identifier, spaceType, carNum, LocalDateTime.now(), permitType, univid, null);

    nvpermitDao.insert(nonVisitorPermit);

    Vehicle vehicle = new Vehicle(carNum, color, licensePlate, manufacturer, model, year);

    vehicleDao.insert(
        vehicle.getCarNum(),
        vehicle.getManufacturer(),
        vehicle.getModel(),
        vehicle.getYear(),
        vehicle.getColor(),
        vehicle.getLicensePlate());

    return null;
  }
}
