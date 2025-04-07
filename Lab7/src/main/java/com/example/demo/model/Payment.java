package com.example.demo.model;

import jakarta.persistence.*;
/**
 * Абстрактный класс Payment и его наследники (Cash, Check, Credit).
 * Используем стратегию наследования SINGLE_TABLE.
 */
@Entity
@Table(name = "payments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float amount;
}
