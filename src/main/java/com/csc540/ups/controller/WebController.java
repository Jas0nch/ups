package com.csc540.ups.controller;

import com.csc540.ups.dao.SpaceDao;
import com.csc540.ups.enums.SpaceType;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {

  @Resource
  SpaceDao spaceDao;

  @ModelAttribute
  @RequestMapping(value = "/test")
  public String test(Model m) {
    String uuid = UUID.randomUUID().toString();
    spaceDao.insert(uuid, 2, SpaceType.electric);
    m.addAttribute("v", spaceDao.select(uuid).getSpaceType());
    m.addAttribute("k", spaceDao.select(uuid).getSpaceNum());
    return "test";
  }

}
