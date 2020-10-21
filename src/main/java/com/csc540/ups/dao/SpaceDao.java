package com.csc540.ups.dao;

import com.csc540.ups.entity.Space;
import com.csc540.ups.enums.SpaceStatus;
import com.csc540.ups.enums.SpaceType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SpaceDao {

  Space select(@Param("id") String uuid);

  void insert(
      @Param("id") String id, @Param("spaceNum") int spaceNum, @Param("type") SpaceType type,
      @Param("zoneID") String zoneID, SpaceStatus status);

  List<Space> selectAllByZoneID(@Param("zoneID") String zoneID);

  void update(@Param("id") String id, @Param("zoneID") String zoneID);

  void updateType(@Param("id") String id, @Param("type") SpaceType type);

  void updateStatus(@Param("id") String id, @Param("status") SpaceStatus status);
}
