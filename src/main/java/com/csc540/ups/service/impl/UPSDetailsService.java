package com.csc540.ups.service.impl;

import com.csc540.ups.dao.UserDao;
import com.csc540.ups.entity.User;
import com.csc540.ups.service.UserService;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UPSDetailsService implements UserDetailsService, UserService {

  @Resource
  UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    User u = login(username);
    return login(username);
  }

  @Override
  public User login(String username) {
    User user = userDao.login(username);
//    if (user == null){
//      user = new User(UUID.randomUUID().toString(), username, "visitor", "visitor");
//    }
    return user;
  }
}
