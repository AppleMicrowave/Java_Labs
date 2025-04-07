package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Long> {
}
