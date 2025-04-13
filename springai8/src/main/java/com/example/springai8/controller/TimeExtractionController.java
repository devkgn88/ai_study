package com.example.springai8.controller;

import com.example.springai8.service.TimeExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.springai8.domain.TimeExtractionRequest;
import com.example.springai8.domain.TimeExtractionResponse;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class TimeExtractionController {
    private final TimeExtractionService timeExtractionService;

    @GetMapping("/")
    public TimeExtractionResponse extractTime(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int date,
            @RequestParam String content
    ) {
        LocalDate locatDate = LocalDate.of(year, month, date);
        TimeExtractionRequest request = new TimeExtractionRequest(locatDate, content);
        return timeExtractionService.extractTime(request);
    }

}
