package com.openfinance.api.exception;

public class PaymentNotFoundExecption extends RuntimeException {
    public PaymentNotFoundExecption(String message) {
        super(message);
    }
}
