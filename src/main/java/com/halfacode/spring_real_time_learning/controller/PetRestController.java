package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.entity.Owner;
import com.halfacode.spring_real_time_learning.entity.Pet;
import com.halfacode.spring_real_time_learning.entity.PetType;
import com.halfacode.spring_real_time_learning.entity.Visit;
import com.halfacode.spring_real_time_learning.repository.OwnerRepository;
import com.halfacode.spring_real_time_learning.repository.PetTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners/{ownerId}/pets")
public class PetRestController {

    private final OwnerRepository owners;
    private final PetTypeRepository types;

    public PetRestController(OwnerRepository owners, PetTypeRepository types) {
        this.owners = owners;
        this.types = types;
    }

    @GetMapping("/types")
    public ResponseEntity<Collection<PetType>> getPetTypes() {
        return ResponseEntity.ok(types.findPetTypes());
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPet(@PathVariable int ownerId, @PathVariable int petId) {
        Owner owner = findOwnerById(ownerId);
        Pet pet = owner.getPet(petId);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }

    @PostMapping
    public ResponseEntity<?> createPet(@PathVariable int ownerId, @RequestBody Pet pet) {
        Owner owner = findOwnerById(ownerId);

        if (pet.getType() != null && pet.getType().getId() != null) {
            pet.setType(
                    types.findById(pet.getType().getId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid PetType ID"))
            );
        }

        if (StringUtils.hasText(pet.getName()) && owner.getPet(pet.getName(), true) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet with this name already exists");
        }

        if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Birth date cannot be in the future");
        }

        owner.addPet(pet);
        owners.save(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }


    @PutMapping("/{petId}")
    public ResponseEntity<?> updatePet(@PathVariable int ownerId, @PathVariable int petId,
                                      @RequestBody Pet pet) {
        Owner owner = findOwnerById(ownerId);
        Pet existingPet = owner.getPet(petId);

        if (existingPet == null) {
            return ResponseEntity.notFound().build();
        }

        // ✅ Fetch PetType from DB
        if (pet.getType() != null && pet.getType().getId() != null) {
            pet.setType(
                    types.findById(pet.getType().getId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid PetType ID"))
            );
        }

        if (StringUtils.hasText(pet.getName())) {
            Pet duplicatePet = owner.getPet(pet.getName(), false);
            if (duplicatePet != null && !duplicatePet.getId().equals(petId)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet with this name already exists");
            }
        }

        if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Birth date cannot be in the future");
        }

        existingPet.setName(pet.getName());
        existingPet.setBirthDate(pet.getBirthDate());
        existingPet.setType(pet.getType());

        owners.save(owner);
        return ResponseEntity.ok(existingPet);
    }


    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable int ownerId, @PathVariable int petId) {
        Owner owner = findOwnerById(ownerId);
        Pet pet = owner.getPet(petId);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        owner.getPets().remove(pet);
        owners.save(owner);
        return ResponseEntity.noContent().build();
    }

    private Owner findOwnerById(int ownerId) {
        return owners.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with id: " + ownerId));
    }

   /* @PostMapping("/{petId}/visits")
    public ResponseEntity<?> addVisit(
            @PathVariable int ownerId,
            @PathVariable int petId,
            @RequestBody Visit visit) {

        Owner owner = findOwnerById(ownerId);
        Pet pet = owner.getPet(petId);

        if (pet == null) {
            return ResponseEntity.notFound().build();
        }

        // Default visit date to now if not provided
        if (visit.getDate() == null) {
            visit.setDate(LocalDate.now());
        }

        pet.addVisit(visit);
        owners.save(owner); // cascade will save the visit

        return ResponseEntity.status(HttpStatus.CREATED).body(visit);
    }*/
    @PostMapping("/{petId}/visits")
    public ResponseEntity<?> addVisit(
            @PathVariable int ownerId,
            @PathVariable int petId,
            @RequestBody Visit visit) {

        Owner owner = findOwnerById(ownerId);
        Pet pet = owner.getPet(petId);

        if (pet == null) {
            return ResponseEntity.notFound().build();
        }

        if (visit.getDate() == null) {
            visit.setDate(LocalDate.now());
        }

        pet.addVisit(visit); // assuming Pet has addVisit(Visit) with cascade

        owners.save(owner);

        return ResponseEntity.status(HttpStatus.CREATED).body(visit);
    }
}