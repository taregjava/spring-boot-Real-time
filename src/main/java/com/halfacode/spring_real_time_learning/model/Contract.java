package com.halfacode.spring_real_time_learning.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contracts")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_number", unique = true)
    private String contractNumber;

}