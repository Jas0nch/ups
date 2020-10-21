package com.csc540.ups.service.impl;

import com.csc540.ups.dao.CitationDao;
import com.csc540.ups.dao.NotificationDao;
import com.csc540.ups.dao.ParkingLotDao;
import com.csc540.ups.dao.VehicleDao;
import com.csc540.ups.entity.Citation;
import com.csc540.ups.entity.NonVisitorPermit;
import com.csc540.ups.entity.Notification;
import com.csc540.ups.entity.Vehicle;
import com.csc540.ups.entity.VisitorPermit;
import com.csc540.ups.enums.CitationStatus;
import com.csc540.ups.enums.CitationType;
import com.csc540.ups.service.CitationService;
import com.csc540.ups.service.NonVisitorPermitService;
import com.csc540.ups.service.VisitorPermitService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitationServiceImpl implements CitationService {

  @Resource
  CitationDao citationDao;

  @Resource
  NotificationDao notificationDao;

  @Resource
  VehicleDao vehicleDao;

  @Autowired
  NonVisitorPermitService nonVisitorPermitService;

  @Autowired
  VisitorPermitService visitorPermitService;


  @Resource
  ParkingLotDao parkingLotDao;

  @Override
  public Citation issue(String carNum, String lotName, CitationType citationType,
      CitationStatus status) {
    Citation citation = new Citation();
    citation.setId(UUID.randomUUID().toString());
    citation.setCarNum(carNum);

    Vehicle vehicle = vehicleDao.select(carNum);

    if (vehicle == null) {
      return null;
    }

    citation.setColor(vehicle.getColor());
    citation.setModel(vehicle.getModel());

    LocalDateTime time = LocalDateTime.now();
    citation.setDate(time);

    String lotID = parkingLotDao.findByName(lotName).getId();
    citation.setLotID(lotID);

    citation.setStatus(CitationStatus.unpaid);
    citation.setType(citationType);
    citation.setTime(time);

    Notification notification = new Notification();
    notification.setCitationID(citation.getId());
    notification.setId(UUID.randomUUID().toString());

    NonVisitorPermit nonVisitorPermit = nonVisitorPermitService.findPermitByCarNum(carNum);
    VisitorPermit visitorPermit = visitorPermitService.selectByCarNum(carNum);

    if (nonVisitorPermit == null && visitorPermit == null) {
      return null;
    }

    if (nonVisitorPermit != null && visitorPermit != null) {
      return null;
    }
    String contact =
        nonVisitorPermit == null ? visitorPermit.getPhone() : nonVisitorPermit.getUnivid();
    notification.setContactInfo(contact);

    citationDao.insert(citation);
    notificationDao.insert(notification);

    return citation;
  }

  @Override
  public List<Citation> selectUnpaidByCarNum(String carNum) {
    return citationDao.selectByCarNum(carNum, CitationStatus.unpaid);
  }

  @Override
  public void pay(String id) {
    citationDao.updateStatusByID(CitationStatus.paid, id);
  }
}
