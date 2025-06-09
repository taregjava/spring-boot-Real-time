package com.halfacode.spring_real_time_learning.repo;

import com.halfacode.spring_real_time_learning.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity,Long> {

    Optional<PermissionEntity> findByName(String name);

}
