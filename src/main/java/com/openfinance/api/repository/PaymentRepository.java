package com.openfinance.api.repository;

import com.openfinance.api.entity.Payment;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(String id);
    List<Payment> findAll();
    List<Payment> findByType(String type);
    List<Payment> findByDateRange(Instant startDate, Instant endDate);
    void deleteById(String id);
    Payment update(Payment payment);
}
