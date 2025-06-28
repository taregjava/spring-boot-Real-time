package com.halfacode.spring_real_time_learning.service;

import java.util.Map;

public interface HttpClientService {

    <T> T httpGet(String url, Map<String, String> params, Class<T> responseType);
    <T, R> T httpPost(String url, Map<String, String> params, Class<T> responseType, R bodyRequest);
    <T, R> T httpPut(String url, Map<String, String> params, Class<T> responseType, R bodyRequest);
    <T> T httpDelete(String url, Map<String, String> params, Class<T> responseType);

}
