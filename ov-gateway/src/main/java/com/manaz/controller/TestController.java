package com.manaz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxw
 * @date 2020/7/17 21:53
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity getPort(HttpServletRequest request, int $_var1, int $_var2) {
        System.out.println($_var1);
        System.out.println($_var2);
        StringBuilder sb = new StringBuilder();
        sb.append("当前访问的服务端口为[" + request.getServerPort() + "]\n");
        sb.append("当前访问的本地端口为[" + request.getLocalPort() + "]\n");
        sb.append("当前访问的远程端口为[" + request.getRemotePort() + "]\n");
        return ResponseEntity.ok(sb.toString());
    }
}
