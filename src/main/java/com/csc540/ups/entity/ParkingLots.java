package com.csc540.ups.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

  public ParkingLots(String name, String address, int spaceNum, int startNum, String initialZone) {
    this.uuid = UUID.randomUUID().toString();
    this.name = name;
    this.address = address;
    this.spaceNum = spaceNum;
    this.startNum = startNum;

    zones = new ArrayList<>();
    Zone initial = new Zone(spaceNum, startNum, initialZone);
    zones.add(initial);
  }

  public void AssignZoneToLot(int total, int start, String zoneName) {
    zones.sort(Comparator.comparingInt(Zone::getStartNum));

    // remove first and then add
    HashSet<Zone> shouldAdd = new HashSet<>();
    HashSet<Zone> shouldRemove = new HashSet<>();

    shouldAdd.add(new Zone(total, start, zoneName));

    int end = total + start - 1;
    for (Zone z : zones) {
      if (z.getEndNum() >= start && z.getStartNum() <= end) {
        shouldRemove.add(z);

        if (z.getEndNum() > start && start > z.getStartNum()) {
          Zone s = new Zone(start - z.getStartNum(), z.getStartNum(), z.getName());
          shouldAdd.add(s);
        }

        if (z.getStartNum() < end && z.getEndNum() > end) {
          Zone s = new Zone(z.getEndNum() - end, end + 1, z.getName());
          shouldAdd.add(s);
        }
      }
    }

    for (Zone z:shouldRemove){
      zones.remove(z);
    }

    zones.addAll(shouldAdd);
  }
}
