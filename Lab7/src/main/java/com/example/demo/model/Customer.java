package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Класс Customer
 */
@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Связь 1 к 1 с Address
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    // Связь 1 ко многим с Order (mappedBy = "customer" указывает на поле customer в Order)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;
}
