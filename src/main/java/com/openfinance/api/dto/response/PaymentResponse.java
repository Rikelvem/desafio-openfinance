package com.openfinance.api.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.openfinance.api.enums.PaymentStatus;
import com.openfinance.api.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimplePaymentResponse.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = AutomaticPaymentResponse.class, name = "AUTOMATIC")
})
public abstract class PaymentResponse {
    private String id;
    private String description;
    private PaymentStatus status;
    private Instant creationDate;
    private BigDecimal amount;
    private PaymentType type;
}
