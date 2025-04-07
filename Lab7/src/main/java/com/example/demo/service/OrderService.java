package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.example.demo.OrderSearchCriteria;
import com.example.demo.OrderSpecification;
import com.example.demo.model.Order;
import com.example.demo.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;

    public List<Order> searchOrders(OrderSearchCriteria criteria) {
        return ordersRepository.findAll(OrderSpecification.build(criteria));
    }
}
