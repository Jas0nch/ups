package com.csc540.ups.dao;


import com.csc540.ups.entity.Citation;
import com.csc540.ups.enums.CitationStatus;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CitationDao {

  int insert(Citation record);

  int insertSelective(Citation record);

  List<Citation> selectByCarNum(String carNum, CitationStatus status);

  void updateStatusByID(CitationStatus status, String id);
}