package com.openfinance.api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimplePaymentResponse extends PaymentResponse{
    private String personDocumentNumber;
}
