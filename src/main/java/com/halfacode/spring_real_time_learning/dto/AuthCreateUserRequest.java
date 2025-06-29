package com.halfacode.spring_real_time_learning.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @Valid AuthCreateRoleRequest authCreateRoleRequest) {
}