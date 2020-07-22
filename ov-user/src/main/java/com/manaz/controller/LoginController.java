package com.manaz.controller;

import com.manaz.vo.JsonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 * @date 2020/7/22 22:36
 */
@RestController
@RequestMapping
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity login(String username,String password){
        return ResponseEntity.ok(JsonResult.ok("登录成功"));
    }
}
