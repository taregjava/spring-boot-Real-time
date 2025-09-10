package com.halfacode.spring_real_time_learning.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {
    private String fullName;
    private String email;
    private String departmentName;
    private Set<String> roles; // Flattened role names
}

