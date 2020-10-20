package com.csc540.ups.entity;

import com.csc540.ups.service.RoleToAuthorityService;
import java.io.Serializable;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, Serializable {

  private String id;
  private String username;
  private String password;
  private String role;
  private boolean isEnable = true;
  private boolean isAccountNonExpired = true;
  private boolean isCredentialsNonExpired = true;
  private boolean isAccountNonLocked = true;

  public User(String id, String username, String password, String role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  private RoleToAuthorityService roleToAuthorityService;
//  private RoleToAuthorityService roleToAuthorityService = new RoleToAuthorityService();

  PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return RoleToAuthorityService.getAuthorityByRole(role);
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getPassword() {
    return encoder.encode(password);
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return isEnable;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    isAccountNonExpired = accountNonExpired;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    isAccountNonLocked = accountNonLocked;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    isCredentialsNonExpired = credentialsNonExpired;
  }

  public void setEnable(boolean enable) {
    isEnable = enable;
  }
}
