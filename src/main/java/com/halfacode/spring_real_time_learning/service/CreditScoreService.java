package com.halfacode.spring_real_time_learning.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreditScoreService {
    public int getScore(String nationalId) {
        // Fake external system call
        return new Random().nextInt(400) + 500; // Score between 500–900
    }
}