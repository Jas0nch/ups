package com.csc540.ups.dao;

import com.csc540.ups.entity.NonVisitorPermit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NvpermitDao {

  int deleteByPrimaryKey(String uuid);

  int insert(NonVisitorPermit record);

  int insertSelective(NonVisitorPermit record);

  NonVisitorPermit selectByPrimaryKey(String uuid);

  NonVisitorPermit selectByUnivID(String univid);

  int updateByPrimaryKeySelective(NonVisitorPermit record);

  int updateByPrimaryKey(NonVisitorPermit record);
}