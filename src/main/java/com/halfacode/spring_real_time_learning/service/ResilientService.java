package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.config.IBackEndFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResilientService {

    @Autowired
    private IBackEndFeignClient backEndFeignClient;

    public String create(){
        try{
            ResponseEntity<String> response = backEndFeignClient.create();
            if(response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }catch (Exception e){
            log.error("Create failed: {}", e.getMessage());
        }
        return null;
    }

    public String read(){
        try{
            ResponseEntity<String> response = backEndFeignClient.read();
            if(response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }catch (Exception e){
            log.error("Read failed: {}", e.getMessage());
        }
        return null;
    }
}
