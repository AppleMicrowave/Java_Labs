package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Pet;
import com.example.demo.model.Tag;
import com.example.demo.repository.DBHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

@Service
public class PetService {

    private final DBHandler dbHandler;

    public PetService(@RequestBody DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    

    public Pet getPET(int id) {
        return dbHandler.getPETS().get(id);
    }

//    public void putPET(int id, String name) {
//
//    }

    public void postPET(int id, String name, Tag[] tags, Category category, Pet.Status status) {
        Pet pet = new Pet(id, name, tags, category, status);
        dbHandler.getPETS().put(id, pet);
    }

    public void deletePet(int id) {
        dbHandler.getPETS().remove(id);
    }
}
