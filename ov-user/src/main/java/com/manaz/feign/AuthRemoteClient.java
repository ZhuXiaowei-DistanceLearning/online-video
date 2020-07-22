package com.manaz.feign;

import org.springframework.http.ResponseEntity;

/**
 * @author zxw
 * @date 2020/7/22 20:41
 */
public interface AuthRemoteClient {
    /**
     * 获取token
     * @return
     */
    ResponseEntity auth();
}
