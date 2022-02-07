package com.example.demo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "operation_history")
public class ExchangeOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "source_currency_id")
    private ExchangeRate sourceExchangeRate;
    @ManyToOne
    @JoinColumn(name = "target_currency_id")
    private ExchangeRate targetExchangeRate;
    private Double sourceAmount;
    private Double targetAmount;
    private LocalDate operationDate;


    public ExchangeOperation(ExchangeRate sourceExchangeRate, ExchangeRate targetExchangeRate, Double sourceAmount, Double targetAmount, LocalDate operationDate) {
        this.sourceExchangeRate = sourceExchangeRate;
        this.targetExchangeRate = targetExchangeRate;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.operationDate = operationDate;
    }
}
