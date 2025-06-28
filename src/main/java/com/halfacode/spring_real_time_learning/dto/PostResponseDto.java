package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

@Data
public class PostResponseDto {
    private Integer id;
    private String title;
    private String body;
    private Integer userId;

}
