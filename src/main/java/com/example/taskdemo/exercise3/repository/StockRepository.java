package com.example.taskdemo.exercise3.repository;

import com.example.taskdemo.exercise3.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
