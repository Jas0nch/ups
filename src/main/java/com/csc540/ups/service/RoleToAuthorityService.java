package com.csc540.ups.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class RoleToAuthorityService {
  private static HashMap<String, List<GrantedAuthority>> roleToAuthority;

  static{
    roleToAuthority = new HashMap<>();

    List<GrantedAuthority> user = new ArrayList<>();
    List<GrantedAuthority> admin = new ArrayList<>();

    GrantedAuthority login = new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return "login";
      }
    };

//    user.add(login);
    admin.add(login);

    roleToAuthority.put("user", user);
    roleToAuthority.put("admin", admin);
  }

  public static List<GrantedAuthority> getAuthorityByRole(String role){
    return roleToAuthority.getOrDefault(role, null);
  }
}
