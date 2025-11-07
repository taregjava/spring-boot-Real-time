package com.halfacode.spring_real_time_learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/public")
    public String publicEndpoint() {
        return "hello public";
    }


    @GetMapping("/user")
    public String userEndpoint() {
        return "hello user";
    }


    @GetMapping("/admin")
    public String adminEndpoint() {
        return "hello admin";
    }
}