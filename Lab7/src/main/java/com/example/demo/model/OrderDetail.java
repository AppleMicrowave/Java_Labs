package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Связь 1 к 1 с Quantity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quantity_id")
    private Quantity quantity;

    private String taxStatus;

    // Связь многие к 1 с Item
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "item_id")
    private Item item;

    // Связь многие к 1 с Order
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private Order order;
}
