package com.csc540.ups.entity;

import com.csc540.ups.enums.PermitType;
import com.csc540.ups.enums.SpaceType;
import java.time.LocalDateTime;

public class NonVisitorPermit extends Permit {

  private String univid;
  private String carNum2;

  private Vehicle vehicle2;

  public NonVisitorPermit() {
  }

  public NonVisitorPermit(
      String identifier,
      SpaceType spaceType,
      String carNum,
      LocalDateTime startDate,
      PermitType permitType,
      String univid,
      String carNum2) {
    this(identifier, spaceType, carNum, startDate, permitType);
    this.univid = univid;
    this.carNum2 = carNum2;
  }

  public NonVisitorPermit(
      String identifier, String carNum, LocalDateTime startDate, PermitType permitType) {
    super(identifier, carNum, startDate, permitType);
  }

  public NonVisitorPermit(
      String identifier,
      SpaceType spaceType,
      String carNum,
      LocalDateTime startDate,
      PermitType permitType) {
    super(identifier, spaceType, carNum, startDate, permitType);
  }

  @Override
  public LocalDateTime getExpirationDate() {
    if (getPermitType() == PermitType.Employee) {
      return getStartDate().plusMonths(12);
    } else if (getPermitType() == PermitType.Student) {
      return getStartDate().plusMonths(4);
    }

    return getStartDate();
  }

  public String getUnivid() {
    return univid;
  }

  public void setUnivid(String univid) {
    this.univid = univid;
  }

  public String getCarNum2() {
    if (getPermitType() == PermitType.Student) {
      return null;
    }

    return carNum2;
  }

  public void setCarNum2(String carNum2) {
    this.carNum2 = carNum2;
  }

  public Vehicle getVehicle2() {
    return vehicle2;
  }

  public void setVehicle2(Vehicle vehicle2) {
    this.vehicle2 = vehicle2;
  }
}
