package com.csc540.ups.dao;

import com.csc540.ups.entity.VisitorPermit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VpermitDao {

  int insert(VisitorPermit record);

  int insertSelective(VisitorPermit record);

  VisitorPermit select(String identifier);

  void updateSpaceNum(String identifier);

  VisitorPermit selectByCarNum(String carNum);

  VisitorPermit selectByLotIDAndSpaceNum(String lotID, int spaceNum);
}