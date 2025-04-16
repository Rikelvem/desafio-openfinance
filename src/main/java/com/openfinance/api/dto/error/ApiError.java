package com.openfinance.api.dto.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiError {
    private final String error_code;
    private final String error_message;
}
