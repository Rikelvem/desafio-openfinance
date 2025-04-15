package com.openfinance.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
public class AutomaticPaymentResponse extends PaymentResponse {
    private String businessDocumentNumber;
    private Instant scheduleDate;
}