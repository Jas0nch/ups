package com.csc540.ups.dao;

import com.csc540.ups.entity.VisitorPermit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VpermitDao {

  int insert(VisitorPermit record);

  int insertSelective(VisitorPermit record);

  VisitorPermit select(String identifier);
}