package com.manaz.controller;

import com.manaz.vo.JsonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 * @date 2020/7/23 22:08
 */
@RestController
@RequestMapping("/video")
public class VideoController {
    @GetMapping("/list")
    public ResponseEntity list(){
        System.out.println("请求成功");
        return ResponseEntity.ok(JsonResult.ok());
    }
}
