package com.halfacode.spring_real_time_learning.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSubmissionDto implements Serializable {
    private String userId;
    private String name;
    private String email;
    private String passportNumber;
    private LocalDate dateOfBirth;
}
