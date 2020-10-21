package com.csc540.ups.dao;

import com.csc540.ups.entity.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VehicleDao {

  void insert(
      @Param("carNum") String carNum,
      @Param("manufacturer") String manufacturer,
      @Param("model") String model,
      @Param("year") String year,
      @Param("color") String color,
      @Param("licensePlate") String licensePlate);

  Vehicle select(@Param("carNum") String carNum);

  void update(@Param("carNum") String carNum,
      @Param("manufacturer") String manufacturer,
      @Param("model") String model,
      @Param("year") String year,
      @Param("color") String color,
      @Param("licensePlate") String licensePlate);
}
