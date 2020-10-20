package com.csc540.ups.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle implements Serializable {
  private String carNum;
  private String manufacturer;
  private String model;
  private String year;
  private String color;
  private String licensePlate;
}
