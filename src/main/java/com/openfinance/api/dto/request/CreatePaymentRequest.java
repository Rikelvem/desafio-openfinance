package com.openfinance.api.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.openfinance.api.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateSimplePaymentRequest.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = CreateAutomaticPaymentRequest.class, name = "AUTOMATIC")
})
public abstract class CreatePaymentRequest {
    private PaymentType type;
    private BigDecimal amount;
    private String description;
}
