package com.example.demo.repo;

import com.example.demo.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    ExchangeRate findFirstByCurrencyCodeAndDate(String currency, LocalDate date);
    ExchangeRate findTopByOrderByIdDesc();
    List<ExchangeRate> findByDate(LocalDate date);
}
