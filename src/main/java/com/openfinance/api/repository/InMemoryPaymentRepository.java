package com.openfinance.api.repository;

import com.openfinance.api.entity.Payment;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {
    private final ConcurrentHashMap<String, Payment> payments = new ConcurrentHashMap<>();

    @Override
    public Payment save(Payment payment) {
        if (payment.getId() == null) {
            payment.setId(UUID.randomUUID().toString());
        }
        payments.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Optional<Payment> findById(String id) {
        return Optional.ofNullable(payments.get(id));
    }

    @Override
    public List<Payment> findByType(String type) {
        return payments.values().stream()
                .filter(
                        payment -> payment.getClass().getSimpleName()
                                .toUpperCase()
                                .contains(type.toUpperCase()))
                .toList();
    }

    @Override
    public List<Payment> findByDateRange(Instant startDate, Instant endDate) {
        return payments.values().stream()
                .filter(payment -> !payment.getCreationDate().isBefore(startDate) &&
                        !payment.getCreationDate().isAfter(endDate))
                .toList();
    }

    @Override
    public List<Payment> findAll() {
        return payments.values().stream().toList();
    }

    @Override
    public void deleteById(String id) {
        payments.remove(id);
    }

    @Override
    public Payment update(Payment payment) {
        if (payments.containsKey(payment.getId())) {
            payments.put(payment.getId(), payment);
            return payment;
        }
        return null;
    }
}