package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDTO {
    private Long id;
    private LocalDate date;
    private String doctorName; // Flattened from doctor.name
}