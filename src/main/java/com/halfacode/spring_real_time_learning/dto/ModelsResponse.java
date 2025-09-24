package com.halfacode.spring_real_time_learning.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ModelsResponse {
    private List<Model> models;


    @Data
    public static class Model {
        private String name;
        private String model;
        private String modified_at;
        private long size;
        private String digest;
        private Map<String, Object> details;

    }
}
