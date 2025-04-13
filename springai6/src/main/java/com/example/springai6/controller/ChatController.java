package com.example.springai6.controller;

import com.example.springai6.vo.Person;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        // case 1
        //this.chatClient = builder.build();
        // case 2
        this.chatClient = builder
                .defaultAdvisors(
                        // case 3
                        new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(new InMemoryChatMemory()),
                        // case 4
                        new PromptChatMemoryAdvisor(new InMemoryChatMemory())
                )
                .build();
   }

    @GetMapping("/chat")
    public String home(@RequestParam("message") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/persons")
    List<Person> findAll() {
        PromptTemplate pt = new PromptTemplate("""
                Return a current list of 10 persons if exists or generate a new list with random values.
                Each object should contain an auto-incremented id field.
                Do not include any explanations or additional text.
                """);
       return this.chatClient.prompt(pt.create())
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }

    @GetMapping("/persons/{id}")
    Person findById(@PathVariable("id") String id) {
        PromptTemplate pt = new PromptTemplate("""
                Find and return the object with id {id} in a current list of persons.
                """);
        Prompt p = pt.create(Map.of("id", id));
        return this.chatClient.prompt(p)
                .call()
                .entity(Person.class);
    }
}

