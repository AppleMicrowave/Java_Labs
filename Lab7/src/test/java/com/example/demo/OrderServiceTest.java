package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class OrderServiceTest {

    @Container
    public static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:15-alpine")
                    .withDatabaseName("ordersdb")
                    .withUsername("postgres")
                    .withPassword("secret");

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrdersRepository orderRepository;

    @Test
    public void testSearchOrders() {
        // Подготовка тестовых данных
        Address address = new Address();
        address.setCity("Moscow");
        address.setStreet("Tverskaya");
        address.setZipcode("101000");

        Customer customer = new Customer();
        customer.setName("Ivan");
        customer.setAddress(address);

        // Создадим платеж типа CASH
        Cash cashPayment = new Cash();
        cashPayment.setCashTendered(120.0f);

        Order order = new Order();
        order.setDate(LocalDateTime.now().minusDays(1));
        order.setStatus("NEW");
        order.setCustomer(customer);
        order.setPayment(cashPayment);

        // Сохраним заказ (cascade сохранит и customer, и address, и payment)
        orderRepository.save(order);

        // Создадим критерии поиска
        OrderSearchCriteria criteria = new OrderSearchCriteria();
        criteria.setCity("Moscow");
        criteria.setStartDate(LocalDateTime.now().minusDays(2));
        criteria.setEndDate(LocalDateTime.now());
        criteria.setPaymentMethod("CASH");
        criteria.setCustomerName("Ivan");
        criteria.setOrderStatus("NEW");

        // Выполняем поиск
        List<Order> orders = orderService.searchOrders(criteria);

        assertEquals(1, orders.size(), "Должен быть найден один заказ");
    }
}
