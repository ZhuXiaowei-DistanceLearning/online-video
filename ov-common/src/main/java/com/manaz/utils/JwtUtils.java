package com.manaz.utils;

import com.manaz.security.pojo.UserReam;
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

    public static String generateToken(UserReam userReam, long expireMinutes) {
        String token = Jwts.builder()
                .setPayload(userReam.toString())
                .setExpiration(new Date())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }

    public static Boolean verifyToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            UserReam realm = parseToken(token);
            if(realm == null){
                return false;
            }
            return false;
        }else{
            return false;
        }
    }

    public static UserReam parseToken(String token) {
        return (UserReam) Jwts.parser()
                .setSigningKey(SECRET)
                .parse(token)
                .getBody();
    }
}
