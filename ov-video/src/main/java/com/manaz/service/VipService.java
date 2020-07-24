package com.manaz.service;

import com.manaz.VO.xQueryParam;
import org.springframework.data.domain.Page;

/**
 * @author zxw
 * @date 2020/7/24 20:43
 */
public interface VipService {
    public Page cacaIndex(xQueryParam baseQueryParam);
}
