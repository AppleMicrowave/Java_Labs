package com.example.demo.controller;

import com.example.demo.service.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Pet;

import java.util.*;

@RestController
@RequestMapping("/api/v3/pets")
public class SPController {

    private final PetService petService;

    public SPController() {
        this.petService = new PetService();
    }

    @GetMapping
    public List<Pet> init() {
        return petService.findPets();
    }
}
