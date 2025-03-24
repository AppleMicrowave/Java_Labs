package com.example.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.Category;
import com.example.demo.model.Pet;
import com.example.demo.model.Tag;
import com.example.demo.repository.CatRep;
import com.example.demo.repository.PetRep;
import com.example.demo.repository.TagRep;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

// PetService.java
@Service
@Transactional
@RequiredArgsConstructor
public class PetService {
    private final PetRep petRepository;
    private final CatRep categoryRepository;
    private final TagRep tagRepository;

    public Pet GetPet(Integer id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
        return pet;
    }

    public Pet AddPet(Pet pet) {
        try {
            Pet entityPet = pet;

            validatePet(entityPet);
            processCategory(entityPet);
            processTag(entityPet);
            try {
                petRepository.save(entityPet);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Pet already exists");
            }

            pet.setId(entityPet.getId());
            return pet;
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Database constraint violation");
        }
    }

    public Pet PutPet(Pet petDetails) {
        validatePet(petDetails);
        var id = petDetails.getId();

        if (!petRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }

        return petRepository.save(petDetails);
    }

    public boolean DeletePet(Integer id) {
        if (!petRepository.existsById(id)) {
            return false;
        }else{
            petRepository.deleteById(id);
            return true;
        }
    }

    private void validatePet(Pet pet) {
        if (pet.getName() == null || pet.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet name is required");
        }

        if (!Arrays.stream(Pet.Status.values()).toList().contains(pet.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid pet status");
        }

    }

    public void processCategory(Pet pet) {
        Category sourceCategory = pet.getCategory();
        if (sourceCategory != null) {
            Category category = categoryRepository.findByName(sourceCategory.getName())
                    .orElseGet(() -> categoryRepository.save(sourceCategory));
            pet.setCategory(category);
        } else {
            pet.setCategory(null);
        }
    }

    public void processTag(Pet pet) {
        Set<Tag> tags = pet.getTags();
        Set<Tag> managedTags = new HashSet<>();

        for (Tag tag : tags) {
            Tag managedTag = tagRepository.findByName(tag.getName())
                    .orElseGet(() -> tagRepository.save(tag));
            managedTags.add(managedTag);
        }
        pet.setTags(managedTags);
    }
}
