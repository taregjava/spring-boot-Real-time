package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.service.ResilientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/resilient")
public class ResilienceController {
    @Autowired
    private ResilientService resilientServiceImpl;

    @PostMapping
    public ResponseEntity<String> create(){
        try{
            String response = resilientServiceImpl.create();
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<String> read(){
        try{
            String response = resilientServiceImpl.read();
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}
