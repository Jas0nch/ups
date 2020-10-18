package com.csc540.ups.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Zone {
  private String uuid;
  private String name;
  private int spaceNum;
  private int startNum;

  private List<Space> spaces;

  public Zone(int spaceNum, int startNum, String name){
    this.uuid = UUID.randomUUID().toString();
    this.spaceNum = spaceNum;
    this.startNum = startNum;
    this.name = name;

    spaces = new ArrayList<>();

    for (int i = startNum; i < spaceNum; i++){
      Space space1 = new Space(i);
      spaces.add(space1);
    }
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

enum ZoneType{
  Student,
  Employee,
  Visitor
}
