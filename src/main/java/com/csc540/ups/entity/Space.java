package com.csc540.ups.entity;
import com.csc540.ups.enums.SpaceType;
import java.io.Serializable;
import java.util.UUID;

public class Space implements Serializable {

  private String uuid;
  private int spaceNum;
  private SpaceType spaceType;

  public Space(String uuid, int spaceNum, SpaceType spaceType) {
    this.uuid = uuid;
    this.spaceNum = spaceNum;
    this.spaceType = spaceType;
  }

  public Space(int spaceNum, SpaceType spaceType) {
    this(UUID.randomUUID().toString(), spaceNum, spaceType);
  }


  public Space(int spaceNum) {
    this(spaceNum, SpaceType.regular);
  }

  public void setSpaceType(SpaceType spaceType) {
    this.spaceType = spaceType;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public int getSpaceNum() {
    return spaceNum;
  }

  public void setSpaceNum(int spaceNum) {
    this.spaceNum = spaceNum;
  }

  public SpaceType getSpaceType() {
    return spaceType;
  }
}

