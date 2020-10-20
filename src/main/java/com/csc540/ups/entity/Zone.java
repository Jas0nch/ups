package com.csc540.ups.entity;

import com.csc540.ups.enums.ZoneType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Zone implements Serializable {

  private String uuid;
  private String name;
  private int spaceNum;
  private int startNum;
  private String lotID;

  private List<Space> spaces;

  public Zone() {
  }

  public Zone(String uuid, String name, int spaceNum, int startNum, List<Space> spaces) {
    this.uuid = uuid;
    this.name = name;
    this.spaceNum = spaceNum;
    this.startNum = startNum;
    this.spaces = spaces;
  }

  public Zone(int spaceNum, int startNum, String name, List<Space> spaces, String lotID) {
    this.uuid = UUID.randomUUID().toString();
    this.spaceNum = spaceNum;
    this.startNum = startNum;
    this.name = name;
    this.lotID = lotID;

    this.spaces = new ArrayList<>();

    this.spaces.addAll(spaces);

    for (Space s : spaces) {
      s.setZoneID(uuid);
    }
  }

  public ZoneType getType() {
    if (name.equals("V")) {
      return ZoneType.Visitor;
    } else if (name.endsWith("S")) {
      return ZoneType.Student;
    } else {
      return ZoneType.Employee;
    }
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

  public int getEndNum() {
    return getSpaceNum() + getStartNum() - 1;
  }

  public List<Space> getSpaces() {
    return spaces;
  }

  public void setSpaces(List<Space> spaces) {
    this.spaces = spaces;
  }

  public String getLotID() {
    return lotID;
  }

  public void setLotID(String lotID) {
    this.lotID = lotID;
  }
}
