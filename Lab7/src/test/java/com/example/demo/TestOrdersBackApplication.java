package com.example.demo;

import org.springframework.boot.SpringApplication;

public class TestOrdersBackApplication {

    public static void main(String[] args) {
        SpringApplication.from(OrdersBackApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
