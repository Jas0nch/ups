package com.csc540.ups.controller;

import com.csc540.ups.entity.NonVisitorPermit;
import com.csc540.ups.entity.User;
import com.csc540.ups.entity.VisitorPermit;
import com.csc540.ups.enums.SpaceType;
import com.csc540.ups.service.LotService;
import com.csc540.ups.service.NonVisitorPermitService;
import com.csc540.ups.service.SpaceService;
import com.csc540.ups.service.UserService;
import com.csc540.ups.service.VisitorPermitService;
import com.csc540.ups.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {

  @Autowired
  LotService lotService;

  @Autowired
  ZoneService zoneService;

  @Autowired
  SpaceService spaceService;

  @Autowired
  VisitorPermitService visitorPermitService;

  @ModelAttribute
  @RequestMapping(value = "/test")
  public String test(Model m) {
    //
    //    ParkingLot lot = lotService.createLot("lotname", "lotaddr", 10, 20, "A");
    //    ParkingLot lot2 = lotService.select(lot.getUuid());
    //    m.addAttribute("first", lot2.getAddress());
    //    m.addAttribute("second", lot2.getZoneList());
    //
    //    Zone z = lotService.AssignZoneToLot(3, 24, "B", lot.getUuid());
    //    ParkingLot lot3 = lotService.select(lot.getUuid());
    //
    //    m.addAttribute("third", lot3.getZoneList());

    spaceService.updateType("10aee22e-4c1b-474d-a7a0-1b4c7b7bb156", SpaceType.electric);
    return "test";
  }

  @Autowired
  UserService userService;
  @Autowired
  NonVisitorPermitService nonVisitorPermitService;

  @GetMapping("/searchVisitorPermit")
  public String search(Model m, @RequestParam("identifier") String identifier) {
    VisitorPermit permit = visitorPermitService.search(identifier);
    //    String permit = "test";
    m.addAttribute("permit", permit);

    return "visitor";
  }

  @GetMapping("/requestVisitorPermit")
  public String request(
      Model m,
      @RequestParam("identifier") String identifier,
      @RequestParam("spaceNum") int spaceNum,
      @RequestParam("lotName") String lotName,
      @RequestParam("duration") int duration,
      @RequestParam("spaceType") SpaceType spaceType,
      @RequestParam("carNum") String carNum,
      @RequestParam("year") String year,
      @RequestParam("model") String model,
      @RequestParam("manufacturer") String manufacturer,
      @RequestParam("color") String color,
      @RequestParam("licensePlate") String licensePlate,
      @RequestParam("phone") String phone) {
    VisitorPermit permit = visitorPermitService.getPermit(
        spaceNum,
        lotName,
        duration,
        identifier,
        spaceType,
        carNum,
        year,
        model,
        manufacturer,
        color,
        licensePlate,
        phone);

//    VisitorPermit permit = visitorPermitService.(identifier);
    //    String permit = "test";
    m.addAttribute("permit", permit);

    return "visitor";
  }

  @GetMapping("hello")
  public String hello(Model m) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.login(authentication.getName());

    NonVisitorPermit permit = nonVisitorPermitService.getPermitByUUID(user.getId());
    m.addAttribute("permit", permit);

    return "hello";
  }
}
