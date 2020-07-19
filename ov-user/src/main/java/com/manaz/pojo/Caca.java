package com.manaz.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author zxw
 * @date 2019/11/25 20:42
 */
@Document("caca")
@Data
public class Caca {
    private String id;
    private String title;
    private String voteRate;
    private String img_src;
    private String local_imgPath;
    private String createDate;
    private String videoLink;
    private String url;
    private Double pubTime;
    @Field(name = "时长")
    private String time;
    @Field(name = "浏览")
    private String see;
    @Field(name = "加入日期")
    private String pubDate;
    @Field(value = "标签:")
    private String[] biaoqian;
    @Field(value = "类别:")
    private String[] category;
}
