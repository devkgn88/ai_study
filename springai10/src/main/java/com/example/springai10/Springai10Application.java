package com.example.springai10;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Springai10Application {

	public static void main(String[] args) {
		SpringApplication.run(Springai10Application.class, args);
	}

	@Bean
	public CommandLineRunner runner1(@Autowired VectorStore vectorStore) {
		System.out.println("runner1 : " + vectorStore);
		return args -> {
			List<Document> documents = List.of(
					new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!",
							Map.of("meta1", "meta1")),
					new Document("The World is Big and Salvation Lurks Around the Corner"),
					new Document("You walk forward facing the past and you turn back toward the future.",
							Map.of("meta2", "meta2")));

			vectorStore.add(documents);

			List<Document> results = vectorStore.similaritySearch(SearchRequest.builder().query("Spring").topK(1).build());
			System.out.println(results);

		};
	}
}
