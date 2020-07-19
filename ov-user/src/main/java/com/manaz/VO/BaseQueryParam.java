package com.manaz.VO;

import lombok.Data;

/**
 * @author zxw
 * @date 2019/11/25 21:28
 */
@Data
public class BaseQueryParam {
    private Integer offset;
    private Integer limit;
    private String sort;
    private Boolean DESC;
    private String keyword;
    private String category;
}
