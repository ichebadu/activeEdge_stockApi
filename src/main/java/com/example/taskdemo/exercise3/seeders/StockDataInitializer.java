package com.example.taskdemo.exercise3.seeders;

import com.example.taskdemo.exercise3.model.Stock;
import com.example.taskdemo.exercise3.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockDataInitializer implements CommandLineRunner {

    private final StockRepository stockRepository;
    @Override
    public void run(String... args) throws Exception {
        Stock stock1 = Stock.builder()
                .name("pearls")
                .amount(BigDecimal.valueOf(50.0))
                .createDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .build();

        Stock stock2 = Stock.builder()
                .name("pearls")
                .amount(BigDecimal.valueOf(500.0))
                .createDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .build();

        Stock stock3 = Stock.builder()
                .name("pearls")
                .amount(BigDecimal.valueOf(500.0))
                .createDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .build();

        stockRepository.saveAll(List.of(stock1,stock2,stock3));
    }
}
