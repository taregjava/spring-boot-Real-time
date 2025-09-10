package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.entity.RoleOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleOrganizationRepository extends JpaRepository<RoleOrganization, String> {

    @Query("SELECT r FROM RoleOrganization r WHERE r.roleOrganizationId IN :ids")
    List<RoleOrganization> findByRoleOrganizationIds(@Param("ids") List<String> ids);

}
