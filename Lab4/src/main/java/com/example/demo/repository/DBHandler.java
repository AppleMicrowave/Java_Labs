package com.example.demo.repository;

import com.example.demo.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DBHandler {
    private final HashMap<Integer, Pet> PETS = new HashMap<>();

    public DBHandler() {
    }

    public Pet getPET(int id) {
        return PETS.get(id);
    }

    public void addPET(Pet pet, int id) {

    }

    public HashMap<Integer, Pet> getPETS() {
        return PETS;
    }
}
