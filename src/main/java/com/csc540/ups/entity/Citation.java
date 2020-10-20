package com.csc540.ups.entity;

import com.csc540.ups.enums.CitationType;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
  private String status;

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
}
