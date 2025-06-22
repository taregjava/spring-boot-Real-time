package com.halfacode.spring_real_time_learning.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.halfacode.spring_real_time_learning.model.TempFileDto;
import com.halfacode.spring_real_time_learning.model.UserSubmissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileController {

    private final RedisTemplate<String, Object> redisTemplate;

    @PostMapping(value = "/upload-temp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadTempFiles(
            @RequestParam("userId") String userId,
            @RequestPart("files") List<MultipartFile> files) throws IOException {

        if (files.size() > 4) {
            return ResponseEntity.badRequest().body("Maximum 4 files allowed.");
        }

        List<TempFileDto> fileDtos = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                fileDtos.add(TempFileDto.builder()
                        .fileName(StringUtils.cleanPath(file.getOriginalFilename()))
                        .contentType(file.getContentType())
                        .content(file.getBytes())
                        .build());
            }
        }

        String redisKey = "tempFiles:" + userId;
        redisTemplate.opsForValue().set(redisKey, fileDtos, Duration.ofMinutes(15));

        System.out.println("Files saved in Redis under key: " + redisKey);

        return ResponseEntity.ok("Files stored temporarily in Redis.");
    }

    @GetMapping("/confirmation/{userId}")
    public ResponseEntity<?> getTempFiles(@PathVariable String userId) {
        String redisKey = "tempFiles:" + userId;
        Object raw = redisTemplate.opsForValue().get(redisKey);

        if (raw == null) {
            return ResponseEntity.notFound().build();
        }


        List<TempFileDto> files;
        try {
            files = (List<TempFileDto>) raw;
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Invalid data format in Redis.");
        }

        System.out.println("Files retrieved from Redis key: " + redisKey);

        return ResponseEntity.ok(files);
    }


    @PostMapping("/submit")
    public ResponseEntity<String> submitFinalRequest(
            @RequestBody UserSubmissionDto userInfo) {

        String userId = userInfo.getUserId();

        String redisKey = "tempFiles:" + userId;
        List<TempFileDto> tempFiles = (List<TempFileDto>) redisTemplate.opsForValue().get(redisKey);

        System.out.println("Files fetched from Redis key: " + redisKey);

        if (tempFiles == null || tempFiles.isEmpty()) {
            return ResponseEntity.badRequest().body("No files found for user.");
        }

        String folderPath = "files_db/" + userId;
        File userFolder = new File(folderPath);

        if (!userFolder.exists()) {
            userFolder.mkdirs();
        }

        try {
            for (TempFileDto fileDto : tempFiles) {
                Path filePath = Paths.get(folderPath, fileDto.getFileName());
                Files.write(filePath, fileDto.getContent());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            File jsonFile = new File(folderPath + "/user-info.json");
            mapper.writeValue(jsonFile, userInfo);

            redisTemplate.delete(redisKey);

            return ResponseEntity.ok("Files and user info saved to local folder.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save: " + e.getMessage());
        }

    }

    @GetMapping("/check")
    public ResponseEntity<?> checkRedisKey(@RequestParam String userId) {
        String redisKey = "tempFiles:" + userId;
        Object redisData = redisTemplate.opsForValue().get(redisKey);

        if (redisData == null) {
            return ResponseEntity.badRequest().body("No files found for user: " + userId);
        }

        System.out.println("Redis data found under key: " + redisKey);

        return ResponseEntity.ok(redisData);
    }
}
