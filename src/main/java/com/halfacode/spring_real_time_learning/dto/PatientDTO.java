package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PatientDTO {
    private Long id;
    private String name;
    private String nationalId;

    private String diagnosis;

    private List<AppointmentDTO> upcomingAppointments;
    private Set<String> doctorNames;
}