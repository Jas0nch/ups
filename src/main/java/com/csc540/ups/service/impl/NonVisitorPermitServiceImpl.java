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
import org.springframework.transaction.annotation.Transactional;

@Service
public class NonVisitorPermitServiceImpl implements NonVisitorPermitService {

  @Resource
  NvpermitDao nvpermitDao;

  @Resource
  VehicleDao vehicleDao;

  @Transactional
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

    Vehicle vehicle = new Vehicle(carNum, manufacturer, model, year, color, licensePlate);

    vehicleDao.insert(
        vehicle.getCarNum(),
        vehicle.getManufacturer(),
        vehicle.getModel(),
        vehicle.getYear(),
        vehicle.getColor(),
        vehicle.getLicensePlate());

    return nonVisitorPermit;
  }

  @Override
  public NonVisitorPermit getPermitByUnivID(String univid) {
    NonVisitorPermit permit = nvpermitDao.selectByUnivID(univid);

    if (permit != null) {
      Vehicle vehicle = vehicleDao.select(permit.getCarNum());
      permit.setVehicle(vehicle);

      if (permit.getCarNum2() != null && !permit.getCarNum2().trim().isEmpty()) {
        Vehicle vehicle2 = vehicleDao.select(permit.getCarNum2());
        permit.setVehicle2(vehicle2);
      }
    }

    return permit;
  }

  @Transactional
  public void changeVehicle(String identifier, String univid, Vehicle vehicle, int i) {
    NonVisitorPermit permit = nvpermitDao.selectByUnivID(univid);
    if (!permit.getIdentifier().equals(identifier)) {
      return;
    }

    vehicleDao.insert(
        vehicle.getCarNum(),
        vehicle.getManufacturer(),
        vehicle.getModel(),
        vehicle.getYear(),
        vehicle.getColor(),
        vehicle.getLicensePlate());

    if (i == 1) {
      permit.setCarNum(vehicle.getCarNum());
    } else if (i == 2) {
      permit.setCarNum2(vehicle.getCarNum());
    } else {
      return;
    }

    nvpermitDao.updateByPrimaryKey(permit);
  }

  @Override
  public NonVisitorPermit findPermitByCarNum(String carNum) {
    return nvpermitDao.selectByCarNum(carNum);
  }

  @Override
  public boolean checkValidParking(String identifier) {
    NonVisitorPermit permit = getPermitByIdentifier(identifier);
    if (permit != null && permit.getPermitType() == PermitType.Employee) {
      return !permit.getExpirationTime().isBefore(LocalDateTime.now());
    } else if (permit != null && permit.getPermitType() == PermitType.Student) {
      LocalDateTime time = LocalDateTime.now();
      return !permit.getExpirationTime().isBefore(time);

//      if  (time.getHour() >= 17 || time.getHour() <= 9 || time.getDayOfWeek() == DayOfWeek.SATURDAY
//          || time.getDayOfWeek() == DayOfWeek.SUNDAY){
//        return true;
//      }
//      else {
//        return false;
//      }
    }

    return true;
  }

  @Override
  public NonVisitorPermit getPermitByIdentifier(String identifier) {
    return nvpermitDao.selectByIdentifier(identifier);
  }
}
