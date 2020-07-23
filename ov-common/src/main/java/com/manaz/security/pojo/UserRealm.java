package com.manaz.security.pojo;

import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @author zxw
 * @date 2020/7/19 10:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRealm {
    private Integer id;
    private String username;
    private List<String> roles;
    private List<String> permissions;
    private Integer exp;
}
