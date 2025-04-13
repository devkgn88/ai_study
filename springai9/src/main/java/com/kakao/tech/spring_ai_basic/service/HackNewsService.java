package com.kakao.tech.spring_ai_basic.service;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class HackNewsService implements Function<HackNewsService.Request, HackNewsService.Response> {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Response apply(Request request) {
        String query = request.query();

        List<String> articles = fetchNewsArticles(query);
        String summary = summarizeArticles(articles);

        return new Response(summary);
    }

    private List<String> fetchNewsArticles(String query) {
        System.out.println("Fetching news articles for query: " + query);
        String url = "https://hn.algolia.com/api/v1/search?query={query}&tags=story";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class, query);

        List<String> articles = new ArrayList<>();

        List<Map<String, Object>> hits = (List<Map<String, Object>>) response.get("hits");

        if (hits != null) {
            for (Map<String, Object> hit : hits) {
                String title = (String) hit.get("title");
                String articleUrl = (String) hit.get("url");
                articles.add(title + "\n" + (articleUrl != null ? articleUrl : ""));
            }
        }

        return articles;
    }

    private String summarizeArticles(List<String> articles) {
        if (articles.isEmpty()) {
            return "해당 주제에 대한 기사를 찾을 수 없습니다.";
        }

        StringBuilder content = new StringBuilder("다음은 요청하신 주제에 대한 최신 뉴스입니다:\n");
        for (String article : articles) {
            content.append("- ").append(article).append("\n");
        }
        return content.toString();
    }

    public record Request(String query, Integer limit) {}
    public record Response(String summary) {}
}
