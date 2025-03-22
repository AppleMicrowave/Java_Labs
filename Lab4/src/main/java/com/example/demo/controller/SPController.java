package com.example.demo.controller;

import com.example.demo.model.ApiResponse;
import com.example.demo.repository.DBHandler;
import com.example.demo.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Pet;

@RestController
@RequestMapping("/api/v3/pet")
public class SPController {

    private final DBHandler dbHandler;
    private final PetsService petsService;


    @PutMapping("/pet")
    public ResponseEntity<Pet> putPet(@RequestBody Pet pet) {
        return petsService.UpdatePet(pet);
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        return petsService.AddPet(pet);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<Pet> getPets(@PathVariable int petId) {
        return petsService.GetPetById(petId);
    }

    @DeleteMapping("/pet/{petId}")
    public void deletePet(@PathVariable int petId) {
        petsService.DeletePetById(petId);
    }
}
