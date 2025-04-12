package com.openfinance.api.entity;

import com.openfinance.api.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AutomaticPayment extends Payment {
    private String businessDocumentNumber;
    private Instant scheduleDate;

    @Override
    public PaymentStatus getDefaultStatus() {
        return PaymentStatus.SCHEDULED;
    }
}
