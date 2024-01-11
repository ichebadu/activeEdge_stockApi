package com.example.taskdemo.exercise3.repository;

import com.example.taskdemo.exercise3.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByName(String name);
}
