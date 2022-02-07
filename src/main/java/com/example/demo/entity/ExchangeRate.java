package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currencyCode;
    private String currencyName;
    private Double rate;
    @Column(name = "rate_date")
    private LocalDate date;


    public Double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return currencyCode + " " + rate.toString();
    }

    public ExchangeRate(String currencyCode, String currencyName, Double rate, LocalDate date) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.rate = rate;
        this.date = date;
    }

}
