package com.csc540.ups.controller;

import com.csc540.ups.entity.Space;
import com.csc540.ups.enums.SpaceType;
import com.csc540.ups.service.SpaceService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {

  @Autowired
  SpaceService spaceService;

  @ModelAttribute
  @RequestMapping(value = "/test")
  public String test(Model m) {
    String uuid = UUID.randomUUID().toString();
    spaceService.insert(new Space(uuid, 2, SpaceType.electric));
    m.addAttribute("v", spaceService.select(uuid).getSpaceType());
    m.addAttribute("k", spaceService.select(uuid).getSpaceNum());
    return "test";
  }

}
