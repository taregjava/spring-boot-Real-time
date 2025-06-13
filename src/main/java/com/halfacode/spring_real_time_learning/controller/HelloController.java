package com.halfacode.spring_real_time_learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    // This is a new comment
    // Adding something new 2
    @GetMapping("/hello")
    public String greet() {
        return "Hello World with REST in Spring Boot";
    }

    // Another comment
    // Adding something 1
    @GetMapping("/hello2")
    public String greet2() {
        return "Hello World with REST in Spring Boot";
    }

    @GetMapping("/hello3")
    public String greet3() {
        return "Hello World with REST in Spring Boot";
    }

    @GetMapping("/hello4")
    public String greet4() {
        return "Hello World with REST in Spring Boot";
    }

    // Another comment
    @GetMapping("/hello6")
    public String greet6() {
        return "Hello World with REST in Spring Boot";
    }
}