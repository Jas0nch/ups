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

    GrantedAuthority adminAu = new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return "admin";
      }
    };

    GrantedAuthority studentAu = new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return "student";
      }
    };

    GrantedAuthority employeeAu = new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return "employee";
      }
    };

    admin.add(adminAu);
    student.add(studentAu);
    employee.add(employeeAu);

    roleToAuthority.put("admin", admin);
    roleToAuthority.put("student", student);
    roleToAuthority.put("employee", employee);
  }

  public static List<GrantedAuthority> getAuthorityByRole(String role){
    return roleToAuthority.getOrDefault(role, null);
  }
}
