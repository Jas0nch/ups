package com.csc540.ups.controller;

import com.csc540.ups.entity.NonVisitorPermit;
import com.csc540.ups.entity.ParkingLot;
import com.csc540.ups.entity.User;
import com.csc540.ups.entity.VisitorPermit;
import com.csc540.ups.enums.PermitType;
import com.csc540.ups.enums.SpaceType;
import com.csc540.ups.service.LotService;
import com.csc540.ups.service.NonVisitorPermitService;
import com.csc540.ups.service.SpaceService;
import com.csc540.ups.service.UserService;
import com.csc540.ups.service.VisitorPermitService;
import com.csc540.ups.service.ZoneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
  @Autowired
  UserService userService;
  @Autowired
  NonVisitorPermitService nonVisitorPermitService;

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

    //    spaceService.updateType("10aee22e-4c1b-474d-a7a0-1b4c7b7bb156", SpaceType.electric);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    m.addAttribute("role", authentication.getName());
    return "test";
  }

  @GetMapping("/searchVisitorPermit")
  public String search(Model m, @RequestParam("identifier") String identifier) {
    VisitorPermit permit = visitorPermitService.search(identifier);
    //    String permit = "test";
    m.addAttribute("permit", permit);
    m.addAttribute("lotname", visitorPermitService.getLotName(permit.getIdentifier()));

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
    VisitorPermit permit =
        visitorPermitService.getPermit(
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

  @GetMapping("/hello")
  public String hello(Model m) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.login(authentication.getName());

    NonVisitorPermit permit = nonVisitorPermitService.getPermitByUUID(user.getId());
    m.addAttribute("permit", permit);

    return "hello";
  }

  @GetMapping("/assignpermit")
  public String assignPermit() {
    return "assignPermit";
  }

  @GetMapping("/assignpermitform")
  public String assignpermitform(
      @RequestParam("univid") String univid,
      @RequestParam("identifier") String identifier,
      @RequestParam("spaceType") SpaceType spaceType,
      @RequestParam("carNum") String carNum,
      @RequestParam("year") String year,
      @RequestParam("model") String model,
      @RequestParam("manufacturer") String manufacturer,
      @RequestParam("color") String color,
      @RequestParam("licensePlate") String licensePlate) {

    User user = userService.findByID(univid);

    PermitType permitType =
        user.getRole().equals("student") ? PermitType.Student : PermitType.Employee;

    nonVisitorPermitService.assignPermit(
        user.getId(),
        permitType,
        identifier,
        spaceType,
        carNum,
        year,
        model,
        manufacturer,
        color,
        licensePlate);

    return "redirect:/hello";
  }

  @GetMapping("/addlot")
  public String addlotpage() {
    return "addlot";
  }

  @GetMapping("/assignzone/{id}")
  public String assign(@PathVariable("id") String id, Model m) {
    m.addAttribute("id", id);
    return "assignzone";
  }

  @GetMapping("/assignzoneform")
  public String assignzoneform(
      @RequestParam("total") int total,
      @RequestParam("start") int start,
      @RequestParam("name") String zoneName,
      @RequestParam("lotID") String lotID) {

    lotService.AssignZoneToLot(total, start, zoneName, lotID);

    return "redirect:/lots";
  }

  @GetMapping("/assigntype/{id}/{lotID}")
  public String assigntype(
      @RequestParam("spaceType") SpaceType spaceType,
      @PathVariable("id") String id,
      @PathVariable("lotID") String lotID,
      Model m) {

    //    SpaceType spaceType = (SpaceType) m.getAttribute("spaceType");
    spaceService.updateType(id, spaceType);
    return "redirect:/spaces/" + lotID;
  }

  @GetMapping("/spaces/{lotID}")
  public String spaces(Model m, @PathVariable("lotID") String lotID) {
    ParkingLot lot = lotService.select(lotID);

    m.addAttribute("list", lot.getSpaces());
    m.addAttribute("lotID", lotID);
    m.addAttribute("spaceType", "");

    return "spaces";
  }

  @GetMapping("/addlotform")
  public String addlotform(
      @RequestParam("name") String name,
      @RequestParam("address") String address,
      @RequestParam("numberOfSpace") int numberOfSpace,
      @RequestParam("beginNumOfSpace") int beginNumOfSpace,
      @RequestParam("initialZone") String initialZone) {
    lotService.createLot(name, address, numberOfSpace, beginNumOfSpace, initialZone);

    return "redirect:/lots";
  }

  @GetMapping("/lots")
  public String lots(Model m) {
    List<ParkingLot> list = lotService.findAll();

    m.addAttribute("list", list);

    return "lots";
  }
}
