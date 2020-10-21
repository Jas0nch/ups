package com.csc540.ups.dao;


import com.csc540.ups.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NotificationDao {

  int insert(Notification record);

  int insertSelective(Notification record);
}