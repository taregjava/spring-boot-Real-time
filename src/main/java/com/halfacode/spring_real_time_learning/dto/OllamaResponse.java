package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

@Data
public class OllamaResponse {
    private String model;
    private String response;
    private boolean done;
}
