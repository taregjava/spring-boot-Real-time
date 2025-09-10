package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}
