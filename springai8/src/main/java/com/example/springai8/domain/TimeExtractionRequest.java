package com.example.springai8.domain;

import java.time.LocalDate;

public record TimeExtractionRequest(
        LocalDate date,
        String content
) {
}
