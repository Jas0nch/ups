package com.csc540.ups.service;

import com.csc540.ups.entity.Citation;
import com.csc540.ups.enums.CitationStatus;
import com.csc540.ups.enums.CitationType;
import java.util.List;

public interface CitationService {

  Citation issue(String carNum, String lotID, CitationType citationType, CitationStatus status);

  List<Citation> selectUnpaidByCarNum(String carNum);

  void pay(String id);
}
