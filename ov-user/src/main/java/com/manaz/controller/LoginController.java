package com.manaz.controller;

import com.manaz.pojo.User;
import com.manaz.security.pojo.UserRealm;
import com.manaz.utils.JwtUtils;
import com.manaz.vo.JsonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        permissonSet.add("/video/update");
        permissonSet.add("/video/get");
        permissonSet.add("/caca/list");
        permissonSet.add("/vip/list");
        permissonSet.add("/video/video");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        UserRealm userRealm = new UserRealm();
        userRealm.setId(1);
        userRealm.setUsername(user.getUsername());
        userRealm.setPermissions(permissonSet);
        userRealm.setRoles(rolesSet);
        String token = JwtUtils.generateToken(userRealm, 60);
        System.out.println("用户token[" + token + "]");
        return ResponseEntity.ok(JsonResult.ok(token));
    }

    @GetMapping("/getInfo")
    public ResponseEntity getInfo(String jwt){
        return ResponseEntity.ok(JwtUtils.getToken(jwt));
    }
}
