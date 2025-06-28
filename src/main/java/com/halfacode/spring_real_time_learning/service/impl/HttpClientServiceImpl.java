package com.halfacode.spring_real_time_learning.service.impl;

import com.halfacode.spring_real_time_learning.exptions.ApiErrorException;
import com.halfacode.spring_real_time_learning.service.HttpClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Map;

@Service
public class HttpClientServiceImpl implements HttpClientService {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientServiceImpl.class);

    private final RestTemplate restTemplate;

    public HttpClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T httpGet(String url, Map<String, String> params, Class<T> responseType) {
        String finalUrl = buildFinalUrl(url, params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.GET, entity, responseType);

        if (!response.getStatusCode().is2xxSuccessful()) {
            String errorMessage = String.format("Error calling API [ %s - %s ], response code: %d",
                    HttpMethod.GET, finalUrl, response.getStatusCode().value());
            logger.error(errorMessage);
            throw new ApiErrorException(errorMessage);
        }

        return response.getBody();
    }

    @Override
    public <T, R> T httpPost(String url, Map<String, String> params, Class<T> responseType, R bodyRequest) {
        String finalUrl = buildFinalUrl(url, params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<R> entity = new HttpEntity<>(bodyRequest, headers);

        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.POST, entity, responseType);

        if (!response.getStatusCode().equals(HttpStatus.OK) && !response.getStatusCode().equals(HttpStatus.CREATED)) {
            String errorMessage = String.format("Error calling API [ %s - %s ], response code: %d",
                    HttpMethod.POST, finalUrl, response.getStatusCode().value());
            logger.error(errorMessage);
            throw new ApiErrorException(errorMessage);
        }

        return response.getBody();
    }

    @Override
    public <T, R> T httpPut(String url, Map<String, String> params, Class<T> responseType, R bodyRequest) {
        String finalUrl = buildFinalUrl(url, params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<R> entity = new HttpEntity<>(bodyRequest, headers);

        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, entity, responseType);

        if (!response.getStatusCode().is2xxSuccessful()) {
            String errorMessage = String.format("Error calling API [ %s - %s ], response code: %d",
                    HttpMethod.PUT, finalUrl, response.getStatusCode().value());
            logger.error(errorMessage);
            throw new ApiErrorException(errorMessage);
        }
        return response.getBody();
    }

    @Override
    public <T> T httpDelete(String url, Map<String, String> params, Class<T> responseType) {
        String finalUrl = buildFinalUrl(url, params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.DELETE, entity, responseType);

        if (!response.getStatusCode().is2xxSuccessful()) {
            String errorMessage = String.format("Error calling API [ %s - %s ], response code: %d",
                    HttpMethod.DELETE, finalUrl, response.getStatusCode().value());
            logger.error(errorMessage);
            throw new ApiErrorException(errorMessage);
        }

        return response.getBody();
    }

    private static String buildFinalUrl(String url, Map<String, String> params) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);

        UriComponents uriComponents = uriBuilder.buildAndExpand(params != null ? params : Collections.emptyMap());

        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                uriBuilder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        return uriComponents.toUriString();
    }
}
