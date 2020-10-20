package com.csc540.ups.dao;

import com.csc540.ups.entity.ParkingLot;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ParkingLotDao {

  ParkingLot select(@Param("id") String id);

  ParkingLot findByName(@Param("name") String name);

  void insert(
      @Param("id") String id,
      @Param("name") String name,
      @Param("address") String address,
      @Param("startNum") int startNum,
      @Param("spaceNum") int spaceNum);

  List<ParkingLot> selectAll();
}
