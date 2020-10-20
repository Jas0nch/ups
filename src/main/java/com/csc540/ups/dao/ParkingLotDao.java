package com.csc540.ups.dao;

import com.csc540.ups.entity.ParkingLot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ParkingLotDao {

  ParkingLot select(@Param("id") String id);

  void insert(
      @Param("id") String id,
      @Param("name") String name,
      @Param("address") String address,
      @Param("startNum") int startNum,
      @Param("spaceNum") int spaceNum);
}
