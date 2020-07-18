package com.manaz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 * @date 2020/7/18 12:12
 */
@RestController
@RequestMapping("/caca")
public class CacaController {
    @GetMapping
    public ResponseEntity getList() {
        return ResponseEntity.ok("request ok");
    }
}
