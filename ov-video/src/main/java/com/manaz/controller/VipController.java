package com.manaz.controller;

import com.manaz.VO.BaseQueryParam;
import com.manaz.VO.xQueryParam;
import com.manaz.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 * @date 2020/6/26 13:44
 */
@RestController
@RequestMapping("/vip")
public class VipController {

    @Autowired
    private VipService vipService;

    @GetMapping("/list")
    public ResponseEntity list(xQueryParam queryParam){
        return ResponseEntity.ok(vipService.cacaIndex(queryParam));
    }

}
