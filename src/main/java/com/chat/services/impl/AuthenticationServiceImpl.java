package com.chat.services.impl;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chat.models.Role;
import com.chat.models.User;
import com.chat.models.dto.JwtAuthenticationResponse;
import com.chat.models.dto.AuthorizationDto;
import com.chat.repositories.UserRepository;
import com.chat.services.AuthenticationService;
import com.chat.services.JwtService;
import com.chat.services.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  
  public JwtAuthenticationResponse signup(AuthorizationDto request) {
      var user = User
                  .builder()
                  .username(request.getUsername())
                  .password(passwordEncoder.encode(request.getPassword()))
                  .role(Role.USER)
                  .build();
      user = userService.save(user);
      var jwt = jwtService.generateToken(user);
      return JwtAuthenticationResponse.builder().token(jwt).build();
  }


  public JwtAuthenticationResponse signin(AuthorizationDto request) {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
      var user = userRepository.findByUsername(request.getUsername())
              .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
      var jwt = jwtService.generateToken(user);
      return JwtAuthenticationResponse.builder().token(jwt).build();
  }
  
}
