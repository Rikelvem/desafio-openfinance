package com.openfinance.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSimplePaymentRequest extends CreatePaymentRequest {
    private String personDocumentNumber;
}
