package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.PostRequestDto;
import com.halfacode.spring_real_time_learning.dto.PostResponseDto;
import com.halfacode.spring_real_time_learning.service.TestApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestApiController {

    private final TestApiService testApiService;

    public TestApiController(TestApiService testApiService) {
        this.testApiService = testApiService;
    }

    @GetMapping("/getPost/{id}")
    public PostResponseDto getPost(@PathVariable int id) {
        return testApiService.getPost(id);
    }

    @PostMapping("/createPost")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return testApiService.createPost(requestDto);
    }

    @PutMapping("/updatePost/{id}")
    public PostResponseDto updatePost(@PathVariable int id, @RequestBody PostRequestDto requestDto) {
        return testApiService.updatePost(id, requestDto);
    }

    @DeleteMapping("/deletePost/{id}")
    public Object deletePost(@PathVariable int id) {
        return testApiService.deletePost(id);
    }
}
