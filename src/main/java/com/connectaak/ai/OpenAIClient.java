package com.connectaak.ai;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
@Slf4j
public class OpenAIClient {

    @Value("${openai.api-key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.openai.com/v1/chat/completions")
            .defaultHeader("Authorization", "Bearer "+apiKey)
            .build();

    public String convertTextToSQL(String userText) {
        String prompt = "Convert the following to a SQL query for a table `employees` with columns id, name, join_year:\n" + userText;
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", new Object[]{
                        Map.of("role", "user", "content", prompt)
                },
                "temperature", 0.2
        );

        Map<String, Object> response = webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response != null) {
            var choices = (java.util.List<Map<String, Object>>) response.get("choices");
            return (String) ((Map<String, Object>) choices.get(0).get("message")).get("content");
        }
        return null;
    }
}