package com.halfacode.spring_real_time_learning.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempFileDto implements Serializable {
    private String fileName;
    private String contentType;
    private byte[] content;
}