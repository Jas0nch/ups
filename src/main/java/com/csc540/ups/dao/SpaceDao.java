package com.csc540.ups.dao;

import com.csc540.ups.entity.Space;
import com.csc540.ups.enums.SpaceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SpaceDao {

  Space select(@Param("id") String uuid);

  void insert(
      @Param("id") String id, @Param("spaceNum") int spaceNum, @Param("type") SpaceType type);
}
