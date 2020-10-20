package com.csc540.ups.service.impl;

import com.csc540.ups.dao.VehicleDao;
import com.csc540.ups.dao.VpermitDao;
import com.csc540.ups.entity.ParkingLot;
import com.csc540.ups.entity.Space;
import com.csc540.ups.entity.Vehicle;
import com.csc540.ups.entity.VisitorPermit;
import com.csc540.ups.entity.Zone;
import com.csc540.ups.enums.PermitType;
import com.csc540.ups.enums.SpaceType;
import com.csc540.ups.enums.ZoneType;
import com.csc540.ups.service.LotService;
import com.csc540.ups.service.VisitorPermitService;
import com.csc540.ups.service.ZoneService;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorPermitServiceImpl implements VisitorPermitService {

  @Autowired
  LotService lotService;
  @Autowired
  ZoneService zoneService;

  @Resource
  VpermitDao vpermitDao;

  @Resource
  VehicleDao vehicleDao;

  @Override
  public VisitorPermit getPermit(
      int spaceNum,
      String lotName,
      int duration,
      String identifier,
      SpaceType spaceType,
      String carNum,
      String year,
      String model,
      String manufacturer,
      String color,
      String licensePlate) {
    ParkingLot lot = lotService.findByName(lotName);

    if (vpermitDao.select(identifier) == null) {
      Space space = null;

      for (Space s : lot.getSpaces()) {
        if (s.getSpaceNum() == spaceNum && s.getSpaceType() == spaceType) {
          space = s;
          break;
        }
      }

      if (space == null) {
        return null;
      }

      Zone zone = zoneService.select(space.getZoneID());
      if (zone == null || zone.getType() != ZoneType.Visitor) {
        return null;
      }

      VisitorPermit visitorPermit =
          new VisitorPermit(
              identifier,
              spaceType,
              carNum,
              LocalDateTime.now(),
              PermitType.Visitor,
              spaceNum,
              LocalDateTime.now(),
              lot.getUuid(),
              duration);

      vpermitDao.insert(visitorPermit);

      Vehicle vehicle = new Vehicle(carNum, color, licensePlate, manufacturer, model, year);

      vehicleDao.insert(
          vehicle.getCarNum(),
          vehicle.getManufacturer(),
          vehicle.getModel(),
          vehicle.getYear(),
          vehicle.getColor(),
          vehicle.getLicensePlate());

      return visitorPermit;
    }

    return null;
  }
}
