package com.manaz.controller;

import com.manaz.security.pojo.UserRealm;
import com.manaz.utils.JwtUtils;
import com.manaz.vo.JsonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zxw
 * @date 2020/7/22 22:36
 */
@RestController
@RequestMapping
public class LoginController {

    private static List<String> rolesSet;
    private static List<String> permissonSet;

    static {
        rolesSet = new ArrayList<>();
        permissonSet = new ArrayList<>();
        rolesSet.add("管理员");
        rolesSet.add("教师");
        rolesSet.add("学生");
        permissonSet.add("/video/update");
        permissonSet.add("/video/get");
        permissonSet.add("/video/list");
        permissonSet.add("/video/delete");
        permissonSet.add("/video/video");
    }

    @PostMapping("/login")
    public ResponseEntity login(String username, String password) {
        UserRealm userRealm = new UserRealm();
        userRealm.setId(1);
        userRealm.setUsername(username);
        userRealm.setPermissions(permissonSet);
        userRealm.setRoles(rolesSet);
        String token = JwtUtils.generateToken(userRealm, 1);
        System.out.println("用户token[" + token + "]");
        return ResponseEntity.ok(JsonResult.ok(token));
    }
}
