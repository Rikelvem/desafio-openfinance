package com.openfinance.api.entity;

import com.openfinance.api.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimplePayment extends Payment {
    private String personDocumentNumber;

    @Override
    public PaymentStatus getDefaultStatus() {
        return PaymentStatus.ACCEPTED;
    }

}
