package com.openfinance.api.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimplePayment extends Payment {
    private String personDocumentNumber;

}
