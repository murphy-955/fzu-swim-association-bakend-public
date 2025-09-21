package com.fzuswimassociation.aspect;

import com.fzuswimassociation.expection.TokenExpiredException;
import com.fzuswimassociation.until.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * token校验
 *
 * @author 李泽聿
 * @since 2025-09-02 15:36
 */

@Component
@Aspect
public class TokenAdvice {
    @Autowired
    private JwtUtil jwtUtil;

    @Pointcut("@annotation(com.fzuswimassociation.annotation.CheckToken)")
    public void checkTokenPointcut() {
    }

    @Before("checkTokenPointcut()")
    public void checkToken(JoinPoint joinPoint) throws TokenExpiredException {
        String obj = joinPoint.getArgs()[0].toString();
        System.out.println(obj);
        // 匹配token
        String regex = "token=(.*?)[,)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(obj);
        String token;
        if (matcher.find()) {
            token = matcher.group(0);
            token = token.substring(6, token.length() - 1);
        } else {
            throw new TokenExpiredException();
        }
        if (jwtUtil.isExpiration(token)) {
            throw new TokenExpiredException();
        }
    }

}
