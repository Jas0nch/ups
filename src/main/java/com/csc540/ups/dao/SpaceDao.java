package com.csc540.ups.dao;

import com.csc540.ups.entity.Space;
import com.csc540.ups.enums.SpaceType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SpaceDao {

  Space select(@Param("id") String uuid);

  void insert(
      @Param("id") String id, @Param("spaceNum") int spaceNum, @Param("type") SpaceType type,
      @Param("zoneID") String zoneID);

  List<Space> selectAllByZoneID(@Param("zoneID") String zoneID);

  void update(@Param("id") String id, @Param("zoneID") String zoneID);
}
