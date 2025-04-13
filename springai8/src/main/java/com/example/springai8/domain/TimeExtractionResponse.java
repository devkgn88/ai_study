package com.example.springai8.domain;

public record TimeExtractionResponse(
        boolean result,
        boolean hasTime,
        String datetime,
        String content
) {
}
