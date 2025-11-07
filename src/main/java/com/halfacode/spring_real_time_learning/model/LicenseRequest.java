package com.halfacode.spring_real_time_learning.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "license_requests")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicenseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "request_number")
    private String requestNumber;

    @Column(name = "municipality_code")
    private Long municipalityCode;
    @OneToOne
    @JoinColumn(name = "contracts_id", referencedColumnName = "id")
    private Contract contract;
    @Column(name = "license_request_step")
    private Integer licenseRequestStep;
    @Column(name = "municipality_name")
    private String municipalityName;

}