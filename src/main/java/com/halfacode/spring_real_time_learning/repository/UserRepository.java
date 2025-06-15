package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.isDeleted = false")
    List<UserEntity> findAllActive();
}