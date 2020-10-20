package com.csc540.ups.controller;

import com.csc540.ups.entity.ParkingLot;
import com.csc540.ups.entity.Zone;
import com.csc540.ups.service.LotService;
import com.csc540.ups.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {

  @Autowired
  LotService lotService;

  @Autowired
  ZoneService zoneService;

  @ModelAttribute
  @RequestMapping(value = "/test")
  public String test(Model m) {

    ParkingLot lot = lotService.createLot("lotname", "lotaddr", 10, 20, "A");
    ParkingLot lot2 = lotService.select(lot.getUuid());
    m.addAttribute("first", lot2.getAddress());
    m.addAttribute("second", lot2.getZoneList());

    Zone z = lotService.AssignZoneToLot(3, 24, "B", lot.getUuid());
    ParkingLot lot3 = lotService.select(lot.getUuid());

    m.addAttribute("third", lot3.getZoneList());

    return "test";
  }
}
