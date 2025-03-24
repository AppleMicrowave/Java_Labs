package com.example.demo.controller;

import com.example.demo.model.Pet;
import com.example.demo.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3")
public class SPController {

    @Autowired
    private final PetService petService;

    @PutMapping("/pet")
    public ResponseEntity<Pet> putPet(@RequestBody Pet pet) {
        try{
            var addedPet = petService.PutPet(pet);
            return ResponseEntity.ok(addedPet);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        try {
            var addedPet = petService.AddPet(pet);
            return ResponseEntity.ok(addedPet);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<Pet> getPets(@PathVariable int petId) {
        Pet pet = petService.GetPet(petId);
        if (pet != null) {
            return new ResponseEntity<>(pet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pet/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable int petId) {
        if (petService.DeletePet(petId)){
            return ResponseEntity.ok().build();
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}