package com.csc540.ups.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

public class RoleToAuthorityService {
  private static final HashMap<String, List<GrantedAuthority>> roleToAuthority;

  static {
    roleToAuthority = new HashMap<>();

    List<GrantedAuthority> admin = new ArrayList<>();
    List<GrantedAuthority> student = new ArrayList<>();
    List<GrantedAuthority> employee = new ArrayList<>();

    GrantedAuthority login = new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return "login";
      }
    };

    admin.add(login);
    student.add(login);
    employee.add(login);

    roleToAuthority.put("admin", admin);
    roleToAuthority.put("student", student);
    roleToAuthority.put("employee", employee);
  }

  public static List<GrantedAuthority> getAuthorityByRole(String role){
    return roleToAuthority.getOrDefault(role, null);
  }
}
