package com.chat.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.models.dto.AuthorizationDto;
import com.chat.models.dto.JwtAuthenticationResponse;
import com.chat.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private static final String signup = "/signup";
    private static final String signin = "/signin";

    private final AuthenticationService authenticationService;
    

    @PostMapping(signup)
    public JwtAuthenticationResponse signup(@RequestBody AuthorizationDto request) {
        return authenticationService.signup(request);
    }

    @PostMapping(signin)
    public JwtAuthenticationResponse signin(@RequestBody AuthorizationDto request) {
        return authenticationService.signin(request);
    }
}