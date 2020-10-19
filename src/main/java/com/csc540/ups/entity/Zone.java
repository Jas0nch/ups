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

  private List<Space> spaces;

  public Zone(int spaceNum, int startNum, String name,
      List<Space> spaces) {
    this.uuid = UUID.randomUUID().toString();
    this.spaceNum = spaceNum;
    this.startNum = startNum;
    this.name = name;

    this.spaces = new ArrayList<>();

    this.spaces.addAll(spaces);
  }

  public ZoneType getType(){
    if (name.equals("V")){
      return ZoneType.Visitor;
    }
    else if (name.endsWith("S")){
      return ZoneType.Student;
    }
    else {
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

  public int getEndNum(){
    return getSpaceNum() + getStartNum() - 1;
  }

  public List<Space> getSpaces() {
    return spaces;
  }

  public void setSpaces(List<Space> spaces) {
    this.spaces = spaces;
  }
}

