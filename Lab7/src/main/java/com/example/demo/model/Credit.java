package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Подкласс Payment: Credit
 */
@Entity
@Data
@DiscriminatorValue("CREDIT")
public class Credit extends Payment {

    private String number;
    private String type;
    private LocalDateTime expDate;
}
