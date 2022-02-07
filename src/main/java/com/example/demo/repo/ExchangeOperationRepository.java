package com.example.demo.repo;

import com.example.demo.entity.ExchangeOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExchangeOperationRepository extends CrudRepository<ExchangeOperation, Long> {
    List<ExchangeOperation> findByOperationDate(LocalDate date);
}
