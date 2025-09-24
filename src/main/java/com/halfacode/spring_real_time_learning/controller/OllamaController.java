package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.ModelsResponse;
import com.halfacode.spring_real_time_learning.service.OllamaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/ollama")
public class OllamaController {

    private final OllamaService ollamaService;

    public OllamaController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String q) throws IOException {
        return ollamaService.generateText(q);
    }

    @GetMapping("/api/models")
    public Mono<ModelsResponse> getModels() {
        return ollamaService.getAvailableModels();
    }
}

