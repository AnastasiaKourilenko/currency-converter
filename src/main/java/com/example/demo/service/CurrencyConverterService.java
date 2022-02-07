package com.example.demo.service;

import com.example.demo.entity.ExchangeRate;
import com.example.demo.repo.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CurrencyConverterService {

    @Autowired
    ExchangeRateRepository exchangeRateRepository;
    @Autowired
    DBUpdateService dbUpdateService;

    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRateRepository.findByDate(exchangeRateRepository.findTopByOrderByIdDesc().getDate());
    }

    public Double convert(String sourceCurrency, String targetCurrency, Double amount, LocalDate localDate) {
            Double sourceExchangeRate = findExchangeRate(sourceCurrency, localDate);
            Double targetExchangeRate = findExchangeRate(targetCurrency, localDate);
            return amount * sourceExchangeRate / targetExchangeRate;

    }

    private Double findExchangeRate(String currencyCode, LocalDate localDate) {
        ExchangeRate exchangeRate = exchangeRateRepository.findFirstByCurrencyCodeAndDate(currencyCode, localDate);
        if (exchangeRate == null) {
            dbUpdateService.getAllRates(localDate);
            exchangeRate = exchangeRateRepository.findFirstByCurrencyCodeAndDate(currencyCode, localDate);
            if (exchangeRate == null) {
                throw new RuntimeException("Нет данных для валюты " + currencyCode);
            }
        }
        return exchangeRate.getRate();
    }
}
