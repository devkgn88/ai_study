package com.kakao.tech.spring_ai_basic;

import org.springframework.ai.image.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/image")
public class ImageController {

    private final ImageModel imageModel;

    public ImageController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping
    public String generateImage(@RequestParam String request) {
        ImageOptions options = ImageOptionsBuilder.builder()
                .model("dall-e-3").width(1024).height(1024).build();

        ImagePrompt prompt = new ImagePrompt(request, options);
        ImageResponse response = imageModel.call(prompt);
        String imageUrl = response.getResult().getOutput().getUrl();
        return "redirect:" + imageUrl;
    }
}
