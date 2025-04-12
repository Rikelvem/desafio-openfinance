package com.openfinance.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AutomaticPaymentResponse extends PaymentResponse{
    private String businessDocumentNumber;
    private Instant scheduleDate;
}
