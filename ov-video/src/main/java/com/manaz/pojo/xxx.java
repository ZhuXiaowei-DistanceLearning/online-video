package com.manaz.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author zxw
 * @date 2019/11/25 20:42
 */
@Document("xxx")
@Data
public class xxx {
    private String id;
    private String title;
    private String catelog;
    private String videoLink;
    private String imgUrl;
    private String date;
    private String createTime;
}
