package com.halfacode.spring_real_time_learning.dto;

import java.time.LocalDateTime;

public record AppointmentDto(
        Long id,
        String doctorName,
        String department,
        LocalDateTime dateTime,
        String userFullName
) {}

