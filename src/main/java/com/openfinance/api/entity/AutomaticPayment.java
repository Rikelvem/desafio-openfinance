package com.openfinance.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AutomaticPayment extends Payment {
    private String businessDocumentNumber;
    private Instant scheduleDate;
}
