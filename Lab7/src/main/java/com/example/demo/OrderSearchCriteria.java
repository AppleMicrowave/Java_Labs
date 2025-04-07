package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderSearchCriteria {
    // Критерии для адреса доставки (находится в Customer -> Address)
    private String city;
    private String street;
    private String zipcode;

    // Временной интервал (по дате заказа)
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // Способ оплаты (например, "CASH", "CHECK", "CREDIT")
    private String paymentMethod;

    // Имя пользователя (Customer.name)
    private String customerName;

    // Статус заказа (Order.status)
    private String orderStatus;
}
