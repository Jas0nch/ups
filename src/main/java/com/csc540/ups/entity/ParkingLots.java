package com.csc540.ups.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class ParkingLots implements Serializable {

  private String uuid;
  private String name;
  private List<Zone> zones;
  private String address;
  private int spaceNum;
  private int startNum;
  private final HashMap<Integer, Space> integerSpaceHashMap;
  private String zoneListString;
  private List<Space> spaces;

  public ParkingLots(String name, String address, int spaceNum, int startNum, String initialZone) {
    this.uuid = UUID.randomUUID().toString();
    this.name = name;
    this.address = address;
    this.spaceNum = spaceNum;
    this.startNum = startNum;

    spaces = new ArrayList<>();
    integerSpaceHashMap = new HashMap<>();

    for (int i = startNum; i < spaceNum; i++) {
      Space space1 = new Space(i);
      spaces.add(space1);
      integerSpaceHashMap.put(i, space1);
    }

    zones = new ArrayList<>();
    Zone initial = new Zone(spaceNum, startNum, initialZone, spaces);
    zones.add(initial);
  }

  public void AssignZoneToLot(int total, int start, String zoneName) {
    zones.sort(Comparator.comparingInt(Zone::getStartNum));

    // remove first and then add
    HashSet<Zone> shouldAdd = new HashSet<>();
    HashSet<Zone> shouldRemove = new HashSet<>();

    shouldAdd.add(new Zone(total, start, zoneName, spaces));

    int end = total + start - 1;
    for (Zone z : zones) {
      if (z.getEndNum() >= start && z.getStartNum() <= end) {
        shouldRemove.add(z);

        if (z.getEndNum() > start && start > z.getStartNum()) {
          List<Space> list = new ArrayList<>();

          for (int i = z.getStartNum(); i < start; i++) {
            list.add(integerSpaceHashMap.get(i));
          }

          Zone s = new Zone(start - z.getStartNum(), z.getStartNum(), z.getName(), list);
          shouldAdd.add(s);
        }

        if (z.getStartNum() < end && z.getEndNum() > end) {
          List<Space> list = new ArrayList<>();

          for (int i = end + 1; i < z.getEndNum(); i++) {
            list.add(integerSpaceHashMap.get(i));
          }

          Zone s = new Zone(z.getEndNum() - end, end + 1, z.getName(), list);
          shouldAdd.add(s);
        }
      }
    }

    for (Zone z : shouldRemove) {
      zones.remove(z);
    }

    zones.addAll(shouldAdd);
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

}
