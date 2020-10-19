package com.csc540.ups.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

enum CitationType {
  InvalidPermit,
  ExpiredPermit,
  NoPermit
}

public class Citation implements Serializable {

  // citation number
  private String uuid;
  private String carNum;
  private String model;
  private String color;
  private LocalDateTime date;
  private String lotID;
  private LocalDateTime time;
  private CitationType type;

  public int getFee() {
    if (type == CitationType.ExpiredPermit) {
      return 25;
    } else if (type == CitationType.InvalidPermit) {
      return 20;
    } else if (type == CitationType.NoPermit) {
      return 40;
    }
    return 0;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getCarNum() {
    return carNum;
  }

  public void setCarNum(String carNum) {
    this.carNum = carNum;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getLotID() {
    return lotID;
  }

  public void setLotID(String lotID) {
    this.lotID = lotID;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public CitationType getType() {
    return type;
  }

  public void setType(CitationType type) {
    this.type = type;
  }
}
