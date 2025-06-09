package com.halfacode.spring_real_time_learning.repo;

import com.halfacode.spring_real_time_learning.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(String username);
    Optional<UserEntity> findByUsername(String username);


}
