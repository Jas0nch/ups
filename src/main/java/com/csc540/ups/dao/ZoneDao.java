package com.csc540.ups.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZoneDao {

  void insert();

  void select();

  void remove(String id);
}
