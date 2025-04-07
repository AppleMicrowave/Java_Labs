package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

/**
 * Подкласс Payment: Check
 */
@Entity
@Data
@DiscriminatorValue("CHECK")
public class Check extends Payment {

    private String name;
    private String bankID;
}
