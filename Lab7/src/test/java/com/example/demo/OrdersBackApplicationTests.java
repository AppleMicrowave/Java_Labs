package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class OrdersBackApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PostgreSQLContainer<?> postgresContainer;

    @Test
    void contextLoads() {
        // Проверяем, что контекст загрузился
        assertThat(applicationContext).isNotNull();
        // Проверяем, что контейнер PostgreSQL запущен
        assertThat(postgresContainer.isRunning()).isTrue();
    }
}
