package com.example.taskdemo.exercise3.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateStockRequest {

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Column(name = "amount", nullable = false)
    @NotBlank(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0.00")
    private BigDecimal amount;
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;}
