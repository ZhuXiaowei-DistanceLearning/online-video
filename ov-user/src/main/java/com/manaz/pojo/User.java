package com.manaz.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author zxw
 * @date 2020/7/19 22:54
 */
@Data
@Document("user")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Long phone;
    private Integer sex;
    private Integer status;
    private Integer usertype;
    private String loginip;
    private String logintime;
    private Integer createby;
    private Date createtime;
    private Integer updateby;
    private Date updatetime;
    private String remark;
}
