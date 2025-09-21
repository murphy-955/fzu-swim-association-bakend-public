package com.fzuswimassociation.until;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Jwt工具类<br>
 * 在application.yaml中token过期时间单位是小时
 *
 * @author 李泽聿
 * @since 2025-09-01 20:15
 */

@Data
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtUtil {

    private  long tokenExpiration; //有效时间,单位毫秒 1000毫秒 == 1秒
    private  String tokenSignKey;  //当前程序签名秘钥

    //生成token字符串
    public  String createToken(Long userId) {
        return Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration*1000*60*60)) //单位小时
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    //从token字符串获取userid
    public  Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .build()
                .parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }



    //判断token是否有效
    public  boolean isExpiration(String token){
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().
                    before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        }catch(Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }
}