package com.manaz.controller;

import com.manaz.VO.BaseQueryParam;
import com.manaz.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxw
 * @date 2019/11/25 20:41
 */
@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/indexCaca")
    public Page Indexcaca(BaseQueryParam baseQueryParam) {
        return indexService.cacaIndex(baseQueryParam);
    }
}
