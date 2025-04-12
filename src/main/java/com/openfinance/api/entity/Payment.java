package com.openfinance.api.entity;

import com.openfinance.api.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public abstract class Payment {
    private String id;
    private BigDecimal amount;
    private Instant creationDate;
    private String description;
    private PaymentStatus status;

    public abstract PaymentStatus getDefaultStatus();

    public PaymentStatus getStatus() {
        return status != null ? status : getDefaultStatus();
    }
}
