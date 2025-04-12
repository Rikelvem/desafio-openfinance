package com.openfinance.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CreateAutomaticPaymentRequest extends CreatePaymentRequest{
    private Instant scheduleDate;
    private String businessDocumentNumber;
}
