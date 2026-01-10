package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer mainServiceCode;
    private Integer subServiceCode;
    private String birthDate;
    private String beneficiaryName;
    private String beneficiaryMobileNumber;
    private Integer beneficiaryDocumentType;
    private String beneficiaryIdNumber;
    private String beneficiaryEmail;
    private String ownerId;
    private Integer beneficiaryIdType;
    private String beneficiaryFirstName;
    private String beneficiaryFatherName;
    private String beneficiaryGrandName;
    private String beneficiaryFamilyName;
    private String beneficiaryBirthDate;
    private String applicantIdNumber;
    private String applicantTypeCode;
    private String applicantName;
    private String applicantMobileNumber;
    private String applicantEmail;
    private String webUserCode;
    private String applicantFirstName;
    private String applicantFatherName;
    private String applicantGrandName;
    private String applicantFamilyName;
    private Integer engineeringOfficeNumber;
    private String applicantBirthDate;
    // response data
    private String contractNumber;
    private String ownerIdNumber;
    private String ownerIdType;
}
