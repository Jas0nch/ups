package com.csc540.ups.dao;

import com.csc540.ups.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
//  User login(@Param("username") String username,@Param("password") String password);
  User login(@Param("username") String username);
}
