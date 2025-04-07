package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Подкласс Payment: Cash
 */
@Entity
@Data
@DiscriminatorValue("CASH")
public class Cash extends Payment {
    private float cashTendered;
}
