package com.openfinance.api.dto.response;

import com.openfinance.api.enums.PaymentStatus;
import com.openfinance.api.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@SuperBuilder
public class PaymentResponse {
    private String id;
    private PaymentType type;
    private BigDecimal amount;
    private Instant creationDate;
    private String description;
    private PaymentStatus status;
}
