package com.kakao.tech.spring_ai_basic;

import com.kakao.tech.spring_ai_basic.service.HackNewsService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@SpringBootApplication
public class SpringAiBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiBasicApplication.class, args);
	}

	@Bean
	public ChatClient chatClient(ChatModel chatModel) {
		return ChatClient.builder(chatModel).build();
	}

	@Bean
	@Description("Hacker News에서 특정 주제의 최신 기사를 가져옵니다.")
	public Function<HackNewsService.Request, HackNewsService.Response> getNews() {
		return new HackNewsService();
	}
}
