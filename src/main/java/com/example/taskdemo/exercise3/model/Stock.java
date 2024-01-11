package com.example.taskdemo.exercise3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Column(name = "amount", nullable = false)
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0.00")
    private BigDecimal currentPrice;
    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
}
