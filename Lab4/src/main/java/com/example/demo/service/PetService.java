package com.example.demo.service;

import com.example.demo.model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    public List<Pet> findPets()
    {
        return List.of(
                new Pet(1, "Bombaclat", new String[]{"Super puper"}, Pet.Status.Available),
                new Pet(1, "Bombaclat", new String[]{"Super puper"}, Pet.Status.Available),
                new Pet(1, "Bombaclat", new String[]{"Super puper"}, Pet.Status.Available)
        );
    }
}
