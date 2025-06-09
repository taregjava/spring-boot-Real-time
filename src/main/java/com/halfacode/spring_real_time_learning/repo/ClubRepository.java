package com.halfacode.spring_real_time_learning.repo;

import com.halfacode.spring_real_time_learning.entity.ClubEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends ListCrudRepository<ClubEntity, Long> {}