package com.openfinance.api.validator;

import com.openfinance.api.exception.InvalidDateRangeException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class DateValidator {
    public void validateDateRange(Instant startDate, Instant endDate) {
        if (startDate == null || endDate == null) {
            throw new InvalidDateRangeException("Ambas as datas devem ser fornecidas");
        }

        if (endDate.isBefore(startDate)) {
            throw new InvalidDateRangeException("A data final deve ser posterior à data inicial");
        }

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        if (daysBetween > 90) {
            throw new InvalidDateRangeException("O intervalo entre as datas não pode ser maior que 90 dias");
        }

        Instant now = Instant.now();
        if (startDate.isAfter(now)) {
            throw new InvalidDateRangeException("A data inicial não pode ser no futuro");
        }
    }
}
