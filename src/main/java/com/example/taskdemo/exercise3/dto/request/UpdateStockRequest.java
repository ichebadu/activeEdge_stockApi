package com.example.taskdemo.exercise3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStockRequest {
    private Long id;
    private String name;
    private BigDecimal currentPrice;

}
