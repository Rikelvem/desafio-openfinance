package com.openfinance.api.service;

import com.openfinance.api.dto.request.CreatePaymentRequest;
import com.openfinance.api.dto.response.PaymentResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PaymentService {
    public PaymentResponse createPayment (CreatePaymentRequest request) {
        //implementation of payment creation logic
        return null;
    }

    public PaymentResponse updatePayment (String paymentId, CreatePaymentRequest request) {
        //implementation of payment update logic
        return null;
    }

    public void deletePayment (String paymentId) {
        //implementation of payment deletion logic
    }

    public PaymentResponse getPayment (String paymentId) {
        //implementation of getting payment details logic
        return null;
    }

    public List<PaymentResponse> getAllPayments (String type, Instant startDate, Instant endDate) {
        if (startDate != null && endDate != null) {
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            if (daysBetween > 90) {
                throw new IllegalArgumentException("O intervalo entre as datas não pode ser maior que 90 dias");
            }
        }

        //implementation of getting all payments logic
        return null;
    }
}
