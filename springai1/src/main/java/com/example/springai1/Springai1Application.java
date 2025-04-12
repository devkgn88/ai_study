package com.example.springai1;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Springai1Application {

	public static void main(String[] args) {

		SpringApplication.run(Springai1Application.class, args);
	}

	@Bean
	public CommandLineRunner runner(ChatModel model){
		System.out.println("초기화 과정에서 자동으로 생성된 ChatModel 객체 : "+model);
		return args->{
			ChatClient chatClient = ChatClient.builder(model).build();
			System.out.println("생성된 ChatClient 객체 : "+chatClient);
			String response  = chatClient.prompt("스티브 잡스의 명언을 세 개 알려줘")
					.call()
					.content();
			System.out.println("[결과] "+response);
		};
	}

}
