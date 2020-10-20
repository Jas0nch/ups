package com.csc540.ups.entity;

import com.csc540.ups.enums.PermitType;
import com.csc540.ups.enums.SpaceType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class Permit implements Serializable {

  protected PermitType permitType;
  private String identifier;
  private String uuid;
  private SpaceType spaceType;
  private String carNum;
  private LocalDateTime startDate;

  Vehicle vehicle;

  public Permit() {
  }

  public Permit(String identifier, String carNum, LocalDateTime startDate, PermitType permitType) {
    this(identifier, SpaceType.regular, carNum, startDate, permitType);
  }

  public Permit(
      String identifier,
      SpaceType spaceType,
      String carNum,
      LocalDateTime startDate,
      PermitType permitType) {
    this.permitType = permitType;
    this.uuid = UUID.randomUUID().toString();
    this.identifier = identifier;
    this.spaceType = spaceType;
    this.carNum = carNum;
    this.startDate = startDate;
    this.permitType = permitType;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public SpaceType getSpaceType() {
    return spaceType;
  }

  public void setSpaceType(SpaceType spaceType) {
    this.spaceType = spaceType;
  }

  public String getCarNum() {
    return carNum;
  }

  public void setCarNum(String carNum) {
    this.carNum = carNum;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public String getZone() {
    StringBuilder sb = new StringBuilder();
    for (char c : identifier.substring(2).toCharArray()) {
      if (Character.isAlphabetic(c)) {
        sb.append(c);
      } else {
        break;
      }
    }

    return sb.toString();
  }

  public LocalDateTime getExpirationDate() {
    return getStartDate().plusMonths(12);
  }

  public LocalDateTime getExpirationTime() {
    return getExpirationDate().plusDays(1).toLocalDate().atStartOfDay();
  }

  public PermitType getPermitType() {
    return permitType;
  }

  public void setPermitType(PermitType permitType) {
    this.permitType = permitType;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }
}
