package com.csc540.ups.entity;
import com.csc540.ups.enums.SpaceStatus;
import com.csc540.ups.enums.SpaceType;
import java.io.Serializable;
import java.util.UUID;

public class Space implements Serializable {

  private String uuid;
  private int spaceNum;
  private SpaceType spaceType;
  private String zoneID;
  private SpaceStatus spaceStatus;

  public Space() {
  }

  public Space(String uuid, int spaceNum, SpaceType spaceType, String zoneID) {
    this.uuid = uuid;
    this.spaceNum = spaceNum;
    this.spaceType = spaceType;
    this.zoneID = zoneID;
    this.spaceStatus = SpaceStatus.use;
  }

  public Space(int spaceNum, SpaceType spaceType, String zoneID) {
    this(UUID.randomUUID().toString(), spaceNum, spaceType, zoneID);
  }

  public Space(int spaceNum, String zoneID) {
    this(spaceNum, SpaceType.regular, zoneID);
  }

  public Space(int spaceNum) {
    this(spaceNum, SpaceType.regular, null);
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

  public String getZoneID() {
    return zoneID;
  }

  public void setZoneID(String zoneID) {
    this.zoneID = zoneID;
  }

  public SpaceStatus getSpaceStatus() {
    return spaceStatus;
  }

  public void setSpaceStatus(SpaceStatus spaceStatus) {
    this.spaceStatus = spaceStatus;
  }
}

