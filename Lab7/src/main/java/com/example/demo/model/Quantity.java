package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("QUANTITY")
public class Quantity extends Measurement {
    private Integer value;
}
