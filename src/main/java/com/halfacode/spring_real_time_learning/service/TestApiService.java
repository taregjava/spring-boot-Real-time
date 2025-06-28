package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.PostRequestDto;
import com.halfacode.spring_real_time_learning.dto.PostResponseDto;
import com.halfacode.spring_real_time_learning.service.HttpClientService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class TestApiService {

    private final HttpClientService httpClientService;

    public TestApiService(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    public PostResponseDto getPost(int id) {
        String url = "https://jsonplaceholder.typicode.com/posts/{id}";
        Map<String, String> params = Collections.singletonMap("id", String.valueOf(id));

        return httpClientService.httpGet(url, params, PostResponseDto.class);
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        String url = "https://jsonplaceholder.typicode.com/posts";
        return httpClientService.httpPost(url, null, PostResponseDto.class, requestDto);
    }

    public PostResponseDto updatePost(int id, PostRequestDto requestDto) {
        String url = "https://jsonplaceholder.typicode.com/posts/{id}";
        Map<String, String> params = Collections.singletonMap("id", String.valueOf(id));
        return httpClientService.httpPut(url, params, PostResponseDto.class, requestDto);
    }

    public Object deletePost(int id) {
        String url = "https://jsonplaceholder.typicode.com/posts/{id}";
        Map<String, String> params = Collections.singletonMap("id", String.valueOf(id));
        return httpClientService.httpDelete(url, params, Object.class);
    }
}