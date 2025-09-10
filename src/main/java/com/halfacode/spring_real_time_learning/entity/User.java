package com.halfacode.spring_real_time_learning.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tbl")
public class User {
    @Id
    private String userId;

    private String email;
    private String firstName;
    private String lastName;
    private String title;
    private String defaultOrganizationId;

   /* @ElementCollection
    private List<String> roleOrganizationIds;*/

    @ManyToMany
    @JoinTable(
            name = "user_role_organization",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_organization_id")
    )
    private List<RoleOrganization> roleOrganizations;
    @ManyToOne
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
