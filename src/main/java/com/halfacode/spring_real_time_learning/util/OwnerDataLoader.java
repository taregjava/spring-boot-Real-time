package com.halfacode.spring_real_time_learning.util;

import com.halfacode.spring_real_time_learning.entity.Owner;
import com.halfacode.spring_real_time_learning.entity.Pet;
import com.halfacode.spring_real_time_learning.entity.PetType;
import com.halfacode.spring_real_time_learning.repository.OwnerRepository;
import com.halfacode.spring_real_time_learning.repository.PetRepository;
import com.halfacode.spring_real_time_learning.repository.PetTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OwnerDataLoader implements CommandLineRunner {

    private final OwnerRepository ownerRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetRepository petRepository;

    public OwnerDataLoader(OwnerRepository ownerRepository,
                           PetTypeRepository petTypeRepository,
                           PetRepository petRepository) {
        this.ownerRepository = ownerRepository;
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) {
        // Create and save a PetType
        PetType dogType = new PetType();
        dogType.setName("Dog");
        petTypeRepository.save(dogType);

        // Create Owner
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setAddress("123 Main Street");
        owner.setCity("Dubai");
        owner.setTelephone("0501234567");

        // Create Pet for Owner
        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setBirthDate(LocalDate.of(2020, 5, 15));
        pet.setType(dogType);

        // Add Pet to Owner
        owner.addPet(pet);

        // Save Owner (cascade will save the Pet as well)
        ownerRepository.save(owner);

        System.out.println("✅ Owner and Pet saved successfully!");
    }
}