package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@DiscriminatorValue("WEIGHT")
public class Weight extends Measurement {
    private BigDecimal value;
}
