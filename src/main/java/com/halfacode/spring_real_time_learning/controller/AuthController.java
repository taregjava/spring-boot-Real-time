package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.AuthCreateUserRequest;
import com.halfacode.spring_real_time_learning.dto.AuthLoginRequest;
import com.halfacode.spring_real_time_learning.dto.AuthResponse;
import com.halfacode.spring_real_time_learning.service.impl.UserDetailServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Controller to perform Authentication")
@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest authLoginRequest) {
        return new ResponseEntity<>(userDetailService.loginUser(authLoginRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUserRequest) {
        return new ResponseEntity<>(userDetailService.createUser(authCreateUserRequest), HttpStatus.OK);
    }
}
