package com.manaz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zxw
 * @date 2020/7/17 21:38
 */
@RestController
public class AuthController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping()
    public ResponseEntity getAuthoriztion(){
        String result = restTemplate.getForObject("http://gateway-service/test?$_var1=100&&$_var2=200",String.class);
        return ResponseEntity.ok(result);
    }
}
