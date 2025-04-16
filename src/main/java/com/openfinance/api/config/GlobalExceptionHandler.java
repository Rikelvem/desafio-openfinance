package com.openfinance.api.config;

import com.openfinance.api.dto.error.ApiError;
import com.openfinance.api.exception.InvalidDateRangeException;
import com.openfinance.api.exception.PaymentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ApiError> handlePaymentNotFoundException(PaymentNotFoundException ex) {
        ApiError error = new ApiError(
                "PAYMENT_NOT_FOUND",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<ApiError> handleInvalidDateRangeException(InvalidDateRangeException ex) {
        ApiError error = new ApiError(
                "INVALID_DATE_RANGE",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneralException(Exception ex) {
        ApiError error = new ApiError(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred: " + ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
