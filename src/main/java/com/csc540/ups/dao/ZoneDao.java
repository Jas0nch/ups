package com.csc540.ups.dao;

import com.csc540.ups.entity.Zone;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ZoneDao {

  void insert(String id, String lotID, String name, int spaceNum, int startNum);

  Zone select(String id);

  void remove(String id);

  List<Zone> selectAllByLotID(String lotID);
}
