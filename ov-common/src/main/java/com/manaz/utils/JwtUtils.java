package com.manaz.utils;

import com.manaz.enums.ExceptionEnums;
import com.manaz.exception.BaseException;
import com.manaz.security.pojo.UserReam;
import com.manaz.vo.JsonResult;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zxw
 * @date 2020/7/19 10:27
 */
public class JwtUtils {
    private static final String SECRET = "SECRET";
    public static final String HEADER_AUTH = "Authorization";

    public static String generateToken(UserReam userReam, long expireMinutes) {
        Long time = System.currentTimeMillis() + 60 * 1000 * expireMinutes;
        String token = Jwts.builder()
                .setPayload(userReam.toString())
                .setExpiration(new Date())
                .setExpiration(new Date(time))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }

    public static JsonResult verifyToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            UserReam realm = getToken(token);
            if (realm == null) {
                throw new BaseException(JsonResult.error(ExceptionEnums.NO_AUTH_ERROR));
            }
            return JsonResult.ok(realm, "验证成功");
        }
        throw new BaseException(JsonResult.error("token值非法"));
    }

    public static UserReam getToken(String token) {
        return (UserReam) Jwts.parser()
                .setSigningKey(SECRET)
                .parse(token)
                .getBody();
    }
}
