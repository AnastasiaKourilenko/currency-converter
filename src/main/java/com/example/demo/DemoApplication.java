package com.example.demo;

import com.example.demo.entity.ExchangeRate;
import com.example.demo.repo.ExchangeRateRepository;
import com.example.demo.service.DBUpdateService;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private DBUpdateService dbUpdateService;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        ExchangeRate exchangeRate = exchangeRateRepository.findFirstByCurrencyCodeAndDate("RUB", LocalDate.now());
        if (exchangeRate == null) {
            dbUpdateService.getAllRates(LocalDate.now());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

