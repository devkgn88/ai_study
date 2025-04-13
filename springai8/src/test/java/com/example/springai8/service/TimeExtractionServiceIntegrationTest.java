package com.example.springai8.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.example.springai8.domain.TimeExtractionRequest;
import com.example.springai8.domain.TimeExtractionResponse;

import java.time.LocalDate;

@SpringBootTest
class TimeExtractionServiceIntegrationTest {

    @Autowired
    private TimeExtractionService timeExtractionService;

    // 스프링의 자동 초기화를 적용한 애
    @Test
    void extractTime() {
        TimeExtractionRequest request = new TimeExtractionRequest(LocalDate.of(2025, 4, 28), "친구들과 밤 11시에 만나기");
        TimeExtractionResponse response = timeExtractionService.extractTime(request);
        System.out.println("응답내용 : " + response);
        Assertions.assertThat(response.result()).isTrue();
        Assertions.assertThat(response.hasTime()).isTrue();
        Assertions.assertThat(response.datetime()).isEqualTo("2025-04-28T23:00:00");
        Assertions.assertThat(response.content()).doesNotContain("11시");
    }
}
