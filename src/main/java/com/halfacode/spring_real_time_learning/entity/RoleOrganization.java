package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleOrganization {
    @Id
    private String roleOrganizationId;
    private String roleName;
}