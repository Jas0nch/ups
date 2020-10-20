package com.csc540.ups.service;

import com.csc540.ups.entity.ParkingLot;
import com.csc540.ups.entity.Zone;
import com.csc540.ups.enums.SpaceType;
import java.util.List;

public interface LotService {

  ParkingLot createLot(
      String name, String address, int numberOfSpace, int beginNumOfSpace, String initialZone);

  Zone AssignZoneToLot(int total, int start, String zoneName, String lotID);

  ParkingLot select(String id);

  void AssignTypeToSpace(String name, int spaceNum, SpaceType spaceType);

  ParkingLot findByName(String name);

  List<ParkingLot> findAll();
}
