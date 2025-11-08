package com.halfacode.spring_real_time_learning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/license")
public class LicenseController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getLicense(@PathVariable String id) {
        return ResponseEntity.ok("License details for " + id);
    }
}
