package com.chat.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.chat.models.User;

public interface UserService {

  UserDetailsService userDetailsService();

  User save(User newUser);

  User getUserById(Long senderId);

}