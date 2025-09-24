package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

@Data
public class OllamaRequest {
    private String model;
    private String prompt;

    public OllamaRequest(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
    }

}