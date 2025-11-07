package com.halfacode.spring_real_time_learning.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "ownership_documents")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnershipDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "document_desc")
    private String documentDesc;

    @Column(name = "disabled")
    private Boolean disabled;

}