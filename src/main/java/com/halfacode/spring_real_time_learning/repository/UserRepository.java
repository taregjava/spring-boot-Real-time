package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

//    @Query("SELECT u FROM UserEntity u WHERE u.username = ?")
//    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findUserEntityByUsername(String username);

}