package com.example.demo.repository;

import com.example.demo.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRep extends JpaRepository<Pet, Integer> {
    Optional<Pet> findByName(String name);
}
