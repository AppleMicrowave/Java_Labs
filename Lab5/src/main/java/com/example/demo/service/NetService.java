package com.example.demo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class NetService {

    @Bean
    public WebMvcConfigurer NetService() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Разрешить все пути
                        .allowedOrigins("*") // Разрешить все домены
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешенные HTTP-методы
                        .allowedHeaders("*"); // Разрешить все заголовки
            }
        };
    }
}
