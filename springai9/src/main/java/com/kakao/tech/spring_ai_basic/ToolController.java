package com.kakao.tech.spring_ai_basic;

import com.kakao.tech.spring_ai_basic.service.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tool")
public class ToolController {

    private final ChatClient chatClient;

    public ToolController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/ko")
    public String tool1() {
        return chatClient.prompt("내일은 몇일일까?")
                .tools(new DateTimeTools())
                .call()
                .content();
    }

    @GetMapping("/en")
    public String tool2() {
        return chatClient.prompt("What day is tomorrow?")
                .tools(new DateTimeTools())
                .call()
                .content();
    }
}
