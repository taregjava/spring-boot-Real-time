package com.halfacode.spring_real_time_learning.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "license")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "license_requests_id", nullable = false)
    private LicenseRequest licenseRequest;

}

