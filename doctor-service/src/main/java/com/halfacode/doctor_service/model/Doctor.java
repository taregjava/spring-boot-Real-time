package com.halfacode.doctor_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "doctor")
public record Doctor(
        @Id Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String specialty,
        String centreName,
        String location
) {
}