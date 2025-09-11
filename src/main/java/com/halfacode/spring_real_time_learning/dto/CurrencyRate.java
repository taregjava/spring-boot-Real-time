package com.halfacode.spring_real_time_learning.dto;


import lombok.Data;

@Data
public class CurrencyRate {
    private String numCode;
    private String charCode;
    private String nominal;
    private String name;
    private String value;
}
