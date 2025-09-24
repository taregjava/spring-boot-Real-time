package com.halfacode.spring_real_time_learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.halfacode.spring_real_time_learning.dto.ModelsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class OllamaService {

    private final WebClient webClient;

    public OllamaService(WebClient webClient) {
        this.webClient = webClient;
    }
    public String generateText(String prompt) {
        String payload = String.format("{\"model\":\"llama3.2:latest\",\"prompt\":\"%s\"}", prompt);

        Flux<String> responseFlux = webClient.post()
                .uri("/api/generate")
                .header("Content-Type", "application/json")
                .bodyValue(payload)
                .retrieve()
                .bodyToFlux(String.class);

        StringBuilder fullResponse = new StringBuilder();

        responseFlux.toStream().forEach(line -> {
            try {
                JsonNode node = new com.fasterxml.jackson.databind.ObjectMapper().readTree(line);
                fullResponse.append(node.get("response").asText());
                if (node.get("done").asBoolean()) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return fullResponse.toString();
    }

    public Mono<ModelsResponse> getAvailableModels() {
        return webClient.get()
                .uri("/api/tags")
                .retrieve()
                .bodyToMono(ModelsResponse.class);
    }
}


   /* public String generateText(String prompt) throws IOException {
        URL url = new URL("http://localhost:11434/api/generate");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        String payload = "{\"model\":\"llama3.2:latest\",\"prompt\":\"" + prompt + "\"}";
        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
        }

        StringBuilder fullResponse = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JsonNode node = new ObjectMapper().readTree(line);
                fullResponse.append(node.get("response").asText());
                if (node.get("done").asBoolean()) break;
            }
        }

        return fullResponse.toString();
    }
}*/
/*
@Service
public class OllamaService {

    private final RestTemplate restTemplate;

    public OllamaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateText(String prompt) {
        String url = "http://localhost:11434/api/generate";

        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama3.2:latest");
        request.put("prompt", prompt);

        // returns raw JSON
        String responseJson = restTemplate.postForObject(url, request, String.class);

        // parse JSON to get the first response
        try {
            JsonNode node = new ObjectMapper().readTree(responseJson);
            return node.get("response").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
*/

/*@Service
public class OllamaService {

    private final RestTemplate restTemplate;

    @Value("${ollama.url:http://localhost:11434}")
    private String ollamaUrl;

    public OllamaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateText(String prompt) {
        String url = ollamaUrl + "/api/generate";

        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama3"); // change to the model you pulled, e.g., mistral, llama3
        request.put("prompt", prompt);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response.getBody();
    }
}*/