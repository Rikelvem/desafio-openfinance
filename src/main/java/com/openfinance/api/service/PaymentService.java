package com.openfinance.api.service;

import com.openfinance.api.dto.request.CreateAutomaticPaymentRequest;
import com.openfinance.api.dto.request.CreatePaymentRequest;
import com.openfinance.api.dto.request.CreateSimplePaymentRequest;
import com.openfinance.api.dto.response.AutomaticPaymentResponse;
import com.openfinance.api.dto.response.PaymentResponse;
import com.openfinance.api.dto.response.SimplePaymentResponse;
import com.openfinance.api.entity.AutomaticPayment;
import com.openfinance.api.entity.Payment;
import com.openfinance.api.entity.SimplePayment;
import com.openfinance.api.enums.PaymentType;
import com.openfinance.api.exception.PaymentNotFoundException;
import com.openfinance.api.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentResponse createPayment (CreatePaymentRequest request) {
        Payment payment;

        if (request.getType() == PaymentType.SIMPLE) {
            CreateSimplePaymentRequest simpleRequest = (CreateSimplePaymentRequest) request;
            payment = new SimplePayment();
            ((SimplePayment) payment).setPersonDocumentNumber(simpleRequest.getPersonDocumentNumber());
        } else {
            CreateAutomaticPaymentRequest automaticRequest = (CreateAutomaticPaymentRequest) request;
            payment = new AutomaticPayment();
            ((AutomaticPayment) payment).setBusinessDocumentNumber(automaticRequest.getBusinessDocumentNumber());
            ((AutomaticPayment) payment).setScheduleDate(automaticRequest.getScheduleDate());
        }

        payment.setAmount(request.getAmount());
        payment.setCreationDate(Instant.now());
        payment.setDescription(request.getDescription());

        if (request.getStatus() != null) {
            payment.setStatus(request.getStatus());
        }

        Payment savedPayment = paymentRepository.save(payment);
        return convertToResponse(savedPayment);
    }

    public PaymentResponse updatePayment (String paymentId, CreatePaymentRequest request) {
        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Pagamento não encontrado com ID: " + paymentId));

        if (request.getType() == PaymentType.SIMPLE && existingPayment instanceof SimplePayment) {
            CreatePaymentRequest simpleRequest = (CreateSimplePaymentRequest) request;
            ((SimplePayment) existingPayment).setPersonDocumentNumber(((CreateSimplePaymentRequest) simpleRequest).getPersonDocumentNumber());
        } else if (request.getType() == PaymentType.AUTOMATIC && existingPayment instanceof AutomaticPayment) {
            CreatePaymentRequest automaticRequest = (CreateAutomaticPaymentRequest) request;
            ((AutomaticPayment) existingPayment).setBusinessDocumentNumber(((CreateAutomaticPaymentRequest) automaticRequest).getBusinessDocumentNumber());
            ((AutomaticPayment) existingPayment).setScheduleDate(((CreateAutomaticPaymentRequest) automaticRequest).getScheduleDate());
        } else {
            throw new IllegalArgumentException("Tipo de pagamento incompatível para atualização");
        }

        existingPayment.setStatus(request.getStatus());

        Payment updatedPayment = paymentRepository.update(existingPayment);
        return convertToResponse(updatedPayment);
    }

    public void deletePayment (String paymentId) {
        if (!paymentRepository.findById(paymentId).isPresent()) {
            throw new PaymentNotFoundException("Pagamento não encontrado com ID: " + paymentId);
        }
        paymentRepository.deleteById(paymentId);
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

    private PaymentResponse convertToResponse(Payment payment) {
        if (payment instanceof SimplePayment) {
            SimplePayment simple = (SimplePayment) payment;
            return SimplePaymentResponse.builder()
                    .id(simple.getId())
                    .type(PaymentType.SIMPLE)
                    .amount(simple.getAmount())
                    .creationDate(simple.getCreationDate())
                    .description(simple.getDescription())
                    .status(simple.getStatus())
                    .personDocumentNumber(simple.getPersonDocumentNumber())
                    .build();
        } else {
            AutomaticPayment automatic = (AutomaticPayment) payment;
            return AutomaticPaymentResponse.builder()
                    .id(automatic.getId())
                    .type(PaymentType.AUTOMATIC)
                    .amount(automatic.getAmount())
                    .creationDate(automatic.getCreationDate())
                    .description(automatic.getDescription())
                    .status(automatic.getStatus())
                    .businessDocumentNumber(automatic.getBusinessDocumentNumber())
                    .scheduleDate(automatic.getScheduleDate())
                    .build();
        }
    }
}
