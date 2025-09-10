package com.halfacode.spring_real_time_learning.controller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @AllArgsConstructor @NoArgsConstructor
public class TrnValidationResponse {
    private String trn;
    private boolean valid;
    private String message;
}