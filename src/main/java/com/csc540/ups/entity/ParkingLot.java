package com.csc540.ups.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class ParkingLot implements Serializable {

  private String uuid;
  private String name;
  private String address;
  private int spaceNum;
  private int startNum;
  private List<Space> spaces;
  private List<Zone> zones;

  public ParkingLot() {
  }

  public ParkingLot(String ID, String name, String address, int startNum, int spaceNum) {
    this.address = address;
    this.name = name;
    this.uuid = ID;
    this.spaceNum = spaceNum;
    this.startNum = startNum;
    zones = new ArrayList<>();
    spaces = new ArrayList<>();
  }

  public ParkingLot(String name, String address, int spaceNum, int startNum, String initialZone) {
    this.uuid = UUID.randomUUID().toString();
    this.name = name;
    this.address = address;
    this.spaceNum = spaceNum;
    this.startNum = startNum;

    spaces = new ArrayList<>();

    zones = new ArrayList<>();
    Zone initial = new Zone(spaceNum, startNum, initialZone, spaces, uuid);
    zones.add(initial);

    for (int i = startNum; i < spaceNum + startNum; i++) {
      Space space1 = new Space(i, initial.getUuid());
      spaces.add(space1);
    }
  }

  public List<String> getZoneList() {
    HashSet<String> res = new HashSet<>();

    for (Zone z : zones) {
      res.add(z.getName());
    }

    return new ArrayList<>(res);
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Zone> getZones() {
    return zones;
  }

  public void setZones(List<Zone> zones) {
    this.zones = zones;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getSpaceNum() {
    return spaceNum;
  }

  public void setSpaceNum(int spaceNum) {
    this.spaceNum = spaceNum;
  }

  public int getStartNum() {
    return startNum;
  }

  public void setStartNum(int startNum) {
    this.startNum = startNum;
  }

  public String getZoneListString() {
    return getZoneList().toString();
  }

  public List<Space> getSpaces() {
    return spaces;
  }

  public void setSpaces(List<Space> spaces) {
    this.spaces = spaces;
  }

  public HashMap<Integer, Space> getIntegerSpaceHashMap() {

    final HashMap<Integer, Space> integerSpaceHashMap = new HashMap<>();

    for (Space s : getSpaces()) {
      integerSpaceHashMap.put(s.getSpaceNum(), s);
    }

    return integerSpaceHashMap;
  }


}
