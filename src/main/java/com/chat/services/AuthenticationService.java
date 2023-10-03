package com.chat.services;

import com.chat.models.dto.AuthorizationDto;
import com.chat.models.dto.JwtAuthenticationResponse;

public interface AuthenticationService {


  
  JwtAuthenticationResponse signup(AuthorizationDto request);


  JwtAuthenticationResponse signin(AuthorizationDto request);
  
}
