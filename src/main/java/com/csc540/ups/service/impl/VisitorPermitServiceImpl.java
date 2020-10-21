package com.csc540.ups.service.impl;

import com.csc540.ups.dao.VehicleDao;
import com.csc540.ups.dao.VpermitDao;
import com.csc540.ups.entity.ParkingLot;
import com.csc540.ups.entity.Space;
import com.csc540.ups.entity.Vehicle;
import com.csc540.ups.entity.VisitorPermit;
import com.csc540.ups.entity.Zone;
import com.csc540.ups.enums.PermitType;
import com.csc540.ups.enums.SpaceStatus;
import com.csc540.ups.enums.SpaceType;
import com.csc540.ups.enums.ZoneType;
import com.csc540.ups.service.LotService;
import com.csc540.ups.service.SpaceService;
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

  @Autowired
  SpaceService spaceService;

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
      String licensePlate,
      String phone) {
    ParkingLot lot = lotService.findByName(lotName);

    if (vpermitDao.select(identifier) == null) {
      Space space = null;

      for (Space s : lot.getSpaces()) {
        if (s.getSpaceNum() == spaceNum && s.getSpaceType() == spaceType) {
          space = s;
          break;
        }
      }

      if (space == null || space.getSpaceStatus() == SpaceStatus.used) {
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
              lot.getId(),
              duration,
              phone);

      vpermitDao.insert(visitorPermit);

      Vehicle vehicle = new Vehicle(carNum, manufacturer, model, year, color, licensePlate);

      vehicleDao.insert(
          vehicle.getCarNum(),
          vehicle.getManufacturer(),
          vehicle.getModel(),
          vehicle.getYear(),
          vehicle.getColor(),
          vehicle.getLicensePlate());

      spaceService.updateStatus(space.getUuid(), SpaceStatus.used);

      return visitorPermit;
    }

    return null;
  }

  @Override
  public VisitorPermit search(String identifier) {
    VisitorPermit permit = vpermitDao.select(identifier);
    if (permit != null) {
      Vehicle vehicle = vehicleDao.select(permit.getCarNum());
      permit.setVehicle(vehicle);
    }
    return permit;
  }

  @Override
  public String getLotName(String identifier) {
    String lotID = search(identifier).getLotID();
    return lotService.select(lotID).getName();
  }

  @Override
  public boolean ExitLot(VisitorPermit permit) { // true overage, false is on time

    ParkingLot lot = lotService.select(permit.getLotID());

    for (Space s : lot.getSpaces()) {
      if (s.getSpaceNum() == permit.getSpaceNum() && s.getSpaceType() == permit.getSpaceType()) {
        spaceService.updateStatus(s.getUuid(), SpaceStatus.use);
        vpermitDao.updateSpaceNum(permit.getIdentifier());
        return !permit.getExpirationTime().isBefore(LocalDateTime.now());
      }
    }

    return true;
  }

  @Override
  public VisitorPermit selectByCarNum(String carNum) {
    return vpermitDao.selectByCarNum(carNum);
  }
}
