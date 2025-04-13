package com.example.springai8.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import com.example.springai8.domain.TimeExtractionRequest;
import com.example.springai8.domain.TimeExtractionResponse;

import java.time.LocalDate;

class TimeExtractionServiceUnitTest {

    String API_KEY = "xxx";

     @Test
    void extractTime() {
        OpenAiApi openAiApi = OpenAiApi.builder()
                .apiKey(API_KEY)
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/openai/")
                .completionsPath("/chat/completions")
                .build();

        OpenAiChatOptions chatOption = OpenAiChatOptions.builder()
                .model("gemini-2.0-flash-lite")
                .temperature(0.0)
                .build();

        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(chatOption)
                .build();

        TimeExtractionService service = new TimeExtractionService(chatModel);
        TimeExtractionRequest request = new TimeExtractionRequest(LocalDate.of(2025, 4, 28), "친구들과 밤 11시에 만나기");
        TimeExtractionResponse response = service.extractTime(request);
        System.out.println("응답내용 : " + response);
        Assertions.assertThat(response.result()).isTrue();
        Assertions.assertThat(response.hasTime()).isTrue();
        Assertions.assertThat(response.datetime()).isEqualTo("2025-04-28T23:00:00");
        Assertions.assertThat(response.content()).doesNotContain("11시");
    }
}
