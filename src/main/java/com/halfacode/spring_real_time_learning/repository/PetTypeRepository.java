package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetTypeRepository extends JpaRepository<PetType, Integer> {

    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    List<PetType> findPetTypes();

}
