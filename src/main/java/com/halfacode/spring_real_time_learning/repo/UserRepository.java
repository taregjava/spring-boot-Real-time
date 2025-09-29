package com.halfacode.spring_real_time_learning.repo;

import com.halfacode.spring_real_time_learning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
