package com.halfacode.spring_real_time_learning.repo;

import com.halfacode.spring_real_time_learning.entity.RoleEntity;
import com.halfacode.spring_real_time_learning.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);

}
