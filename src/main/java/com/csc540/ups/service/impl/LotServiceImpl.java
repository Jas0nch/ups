package com.csc540.ups.service.impl;

import com.csc540.ups.dao.ParkingLotDao;
import com.csc540.ups.entity.ParkingLot;
import com.csc540.ups.entity.Space;
import com.csc540.ups.entity.Zone;
import com.csc540.ups.enums.SpaceType;
import com.csc540.ups.service.LotService;
import com.csc540.ups.service.SpaceService;
import com.csc540.ups.service.ZoneService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LotServiceImpl implements LotService {

  @Autowired
  SpaceService spaceService;

  @Autowired
  ZoneService zoneService;

  @Resource
  ParkingLotDao parkingLotDao;

  @Transactional
  @Override
  public ParkingLot createLot(
      String name, String address, int numberOfSpace, int beginNumOfSpace, String initialZone) {
    ParkingLot lot = new ParkingLot(name, address, numberOfSpace, beginNumOfSpace, initialZone);

    parkingLotDao.insert(
        lot.getId(), lot.getName(), lot.getAddress(), lot.getStartNum(), lot.getSpaceNum());

    for (Zone z : lot.getZones()) {
      zoneService.insert(z);
    }

    for (Space s : lot.getSpaces()) {
      spaceService.insert(s);
    }

    return lot;
  }

  @Transactional
  @Override
  public Zone AssignZoneToLot(int total, int start, String zoneName, String lotID) {
    ParkingLot lot = select(lotID);

    HashMap<Integer, Space> integerSpaceHashMap = lot.getIntegerSpaceHashMap();

    List<Zone> zones = lot.getZones();

    List<Space> spaces = new ArrayList<>();

    for (int i = start; i < start + total; i++) {
      spaces.add(integerSpaceHashMap.get(i));
    }

    zones.sort(Comparator.comparingInt(Zone::getStartNum));

    // remove first and then add
    HashSet<Zone> shouldAdd = new HashSet<>();
    HashSet<Zone> shouldRemove = new HashSet<>();

    Zone zone = new Zone(total, start, zoneName, spaces, lotID);

    for (Space s : zone.getSpaces()) {
      spaceService.update(s);
    }

    shouldAdd.add(zone);

    int end = total + start - 1;
    for (Zone z : zones) {
      if (z.getEndNum() >= start && z.getStartNum() <= end) {
        shouldRemove.add(z);

        if (z.getEndNum() > start && start > z.getStartNum()) {
          List<Space> list = new ArrayList<>();

          for (int i = z.getStartNum(); i < start; i++) {
            list.add(integerSpaceHashMap.get(i));
          }

          Zone newZone =
              new Zone(start - z.getStartNum(), z.getStartNum(), z.getName(), list, lotID);

          for (Space s : newZone.getSpaces()) {
            spaceService.update(s);
          }

          shouldAdd.add(newZone);
        }

        if (z.getStartNum() < end && z.getEndNum() > end) {
          List<Space> list = new ArrayList<>();

          for (int i = end + 1; i <= z.getEndNum(); i++) {
            list.add(integerSpaceHashMap.get(i));
          }

          Zone newZone = new Zone(z.getEndNum() - end, end + 1, z.getName(), list, lotID);

          for (Space s : newZone.getSpaces()) {
            spaceService.update(s);
          }

          shouldAdd.add(newZone);
        }
      }
    }

    for (Zone z : shouldRemove) {
      zoneService.remove(z.getUuid());
      zones.remove(z);
    }

    for (Zone z : shouldAdd) {
      zoneService.insertAndUpdate(z);
      zones.add(z);
    }

    return zone;
  }

  @Override
  public ParkingLot select(String id) {
    ParkingLot lot = parkingLotDao.select(id);

    return fillParkingLot(lot, id);
  }

  @Override
  public ParkingLot findByName(String name) {
    ParkingLot lot = parkingLotDao.findByName(name);

    return fillParkingLot(lot, lot.getId());
  }

  @Override
  public List<ParkingLot> findAll() {
    List<ParkingLot> list = parkingLotDao.selectAll();

    for (ParkingLot p : list) {
      fillParkingLot(p, p.getId());
    }

    return list;
  }

  private ParkingLot fillParkingLot(ParkingLot lot, String uuid) {
    lot.setZones(zoneService.selectAllByLotID(uuid));

    List<Space> spaces = new ArrayList<>();

    for (Zone z : lot.getZones()) {
      spaces.addAll(z.getSpaces());
    }

    lot.setSpaces(spaces);

    return lot;
  }

  @Transactional
  @Override
  public void AssignTypeToSpace(String name, int spaceNum, SpaceType spaceType) {
    ParkingLot lot = parkingLotDao.findByName(name);

    for (Space s : lot.getSpaces()) {
      if (s.getSpaceNum() == spaceNum) {
        spaceService.updateType(s.getUuid(), spaceType);
      }
    }
  }
}
