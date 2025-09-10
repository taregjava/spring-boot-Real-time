package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diagnosis;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
