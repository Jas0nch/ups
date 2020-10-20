package com.csc540.ups.entity;

import com.csc540.ups.enums.PermitType;
import com.csc540.ups.enums.SpaceType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class VisitorPermit extends Permit {

  private int spaceNum;
  private LocalDateTime startTime;
  private String lotID;
  private int duration; // in hours
  private String phone;

  public VisitorPermit() {
  }

  public VisitorPermit(
      String identifier,
      SpaceType spaceType,
      String carNum,
      LocalDateTime startDate,
      PermitType permitType,
      int spaceNum,
      LocalDateTime startTime,
      String lotID,
      int duration,
      String phone) {
    super(identifier, spaceType, carNum, startDate, permitType);

    this.spaceNum = spaceNum;
    this.startTime = startTime;
    this.lotID = lotID;
    this.duration = duration;
    this.phone = phone;
  }

  public VisitorPermit(
      String identifier, String carNum, LocalDateTime startDate, PermitType permitType) {
    super(identifier, carNum, startDate, permitType);
  }

  public VisitorPermit(
      String identifier,
      SpaceType spaceType,
      String carNum,
      LocalDateTime startDate,
      PermitType permitType) {
    super(identifier, spaceType, carNum, startDate, permitType);
  }

  @Override
  public LocalDateTime getExpirationTime() {
    return getStartDate().plusHours(duration);
  }

  @Override
  public LocalDateTime getExpirationDate() {
    return getExpirationTime();
  }


}
