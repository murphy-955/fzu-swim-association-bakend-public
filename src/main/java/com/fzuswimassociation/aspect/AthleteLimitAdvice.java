package com.fzuswimassociation.aspect;

import com.fzuswimassociation.expection.AthleteRegisterOverLimitException;
import com.fzuswimassociation.pojo.vo.AthleteRegisterVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 运动员报名项目数量校验
 *
 * @author 李泽聿
 * @since 2025-09-13 15:00
 */

@Component
@Aspect
public class AthleteLimitAdvice {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(com.fzuswimassociation.annotation.CheckAthleteLimit)")
    public void checkAthleteLimit() {
    }

    @Before("checkAthleteLimit()")
    public void beforeRegister(JoinPoint joinPoint) throws AthleteRegisterOverLimitException {
        AthleteRegisterVo vo = (AthleteRegisterVo) joinPoint.getArgs()[0];
        String competitionId = vo.getCompetitionId();
        String academicNumber = vo.getAcademicNumber();
        // 判断运动员是否超过限制个人允许租到报名项目限制
        Integer athleteLimit, redisCount;
        redisCount = (Integer) redisTemplate.opsForValue()
                .get("competition:" + "register_count:" + competitionId+ academicNumber + "count");
        athleteLimit = (Integer) redisTemplate.opsForValue()
                .get("competition:" + "athlete_limit:" + competitionId);
        if (athleteLimit != null && redisCount > athleteLimit) {
            throw new AthleteRegisterOverLimitException("运动员超过限制个人允许最大报名项目限制");
        }
    }

}
