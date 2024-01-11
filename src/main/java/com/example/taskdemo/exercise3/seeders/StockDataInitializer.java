package com.example.taskdemo.exercise3.seeders;

import com.example.taskdemo.exercise3.model.Stock;
import com.example.taskdemo.exercise3.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockDataInitializer implements CommandLineRunner {

    private final StockRepository stockRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Stock> stockData = Arrays.asList(
                createStock("pearls", 780.0),
                createStock("phone", 500.0),
                createStock("cars", 300.0)
        );

        for (Stock stock : stockData) {
            if (stockRepository.findByName(stock.getName()).isEmpty()) {
                stockRepository.save(stock);
            }
        }
    }

    private Stock createStock(String name, double currentPrice) {
        return Stock.builder()
                .name(name)
                .currentPrice(BigDecimal.valueOf(currentPrice))
                .createDate(new Timestamp(System.currentTimeMillis()))
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
