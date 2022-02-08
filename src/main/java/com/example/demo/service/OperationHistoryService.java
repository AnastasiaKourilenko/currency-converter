package com.example.demo.service;

import com.example.demo.entity.ExchangeOperation;
import com.example.demo.entity.ExchangeRate;
import com.example.demo.repo.ExchangeOperationRepository;
import com.example.demo.repo.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperationHistoryService {
    @Autowired
    private ExchangeOperationRepository exchangeOperationRepository;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public void saveOperation(String sourceCurrency, String targetCurrency, Double sourceAmount, Double targetAmount, LocalDate localDate) {
        ExchangeRate sourceExchangeRate = exchangeRateRepository.findFirstByCurrencyCodeAndDate(sourceCurrency, localDate);
        ExchangeRate targetExchangeRate = exchangeRateRepository.findFirstByCurrencyCodeAndDate(targetCurrency, localDate);
        ExchangeOperation exchangeOperation = new ExchangeOperation(sourceExchangeRate, targetExchangeRate, sourceAmount, targetAmount, localDate);
        exchangeOperationRepository.save(exchangeOperation);
    }

    public List<ExchangeOperation> getAllExchangeOperations(LocalDate date) {
        return exchangeOperationRepository.findByOperationDate(date);
    }
}
