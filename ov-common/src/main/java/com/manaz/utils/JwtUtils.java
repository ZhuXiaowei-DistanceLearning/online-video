package com.manaz.utils;

import com.manaz.enums.ExceptionEnums;
import com.manaz.exception.BaseException;
import com.manaz.security.pojo.UserRealm;
import com.manaz.vo.JsonResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author zxw
 * @date 2020/7/19 10:27
 */
public class JwtUtils {
    private static final String SECRET = "SECRET";
    private static final String SUBJECT = "SUBJECT";
    private static final String PAYLOAD = "PAYLOAD";
    private static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTH = "Authorization";

    public static String generateToken(UserRealm userReam, long expireMinutes) {
        Map claimMap = new HashMap();
        claimMap.put("id", userReam.getId());
        claimMap.put("username", userReam.getUsername());
        claimMap.put("roles", userReam.getRoles());
        claimMap.put("permissons", userReam.getPermissions());
        Long time = System.currentTimeMillis() + 60 * 1000 * expireMinutes;
        String token = Jwts.builder()
                .setSubject(SUBJECT)
                .setClaims(claimMap)
                .setExpiration(new Date(time))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }

    public static JsonResult verifyToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            UserRealm realm = getToken(token);
            if (realm == null) {
                throw new BaseException(JsonResult.error(ExceptionEnums.NO_AUTH_ERROR));
            }
            return JsonResult.ok(realm, "验证成功");
        }
        throw new BaseException(JsonResult.error("token值非法"));
    }

    public static UserRealm getToken(String token) {
        UserRealm userRealm = new UserRealm();
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replaceAll(TOKEN_PREFIX, ""))
                .getBody();
        userRealm.setId((Integer) claims.get("id"));
        userRealm.setUsername((String) claims.get("username"));
        userRealm.setRoles((List<String>) claims.get("roles"));
        userRealm.setPermissions((List<String>) claims.get("permissons"));
        userRealm.setExp((Integer) claims.get("exp"));
        return userRealm;
    }
}
