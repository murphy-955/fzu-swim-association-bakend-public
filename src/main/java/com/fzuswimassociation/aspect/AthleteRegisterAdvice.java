package com.fzuswimassociation.aspect;

import com.fzuswimassociation.expection.*;
import com.fzuswimassociation.pojo.vo.AthleteRegisterVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 将运动员校验业务横切
 *
 * @author 李泽聿
 * @since 2025-09-10 14:27
 */

@Component
@Aspect
public class AthleteRegisterAdvice {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(com.fzuswimassociation.annotation.CheckAthlete)")
    public void checkAthlete() {
    }

    @Before("checkAthlete()")
    public void beforeAthleteRegister(JoinPoint joinPoint) throws AthleteRegisterException, CompetitionException, CollegeException, ActivityException {
        AthleteRegisterVo athleteRegisterVo = (AthleteRegisterVo) joinPoint.getArgs()[0];
        String name = athleteRegisterVo.getName();
        String academicNumber = athleteRegisterVo.getAcademicNumber();
        String competitionId = athleteRegisterVo.getCompetitionId();
        String college = athleteRegisterVo.getCollege();
        String sportType = athleteRegisterVo.getSportType();
        // 判断报名是否截至
        Long ttl = redisTemplate.getExpire("competition:" + "name:" + competitionId, TimeUnit.SECONDS);
        if (ttl <= 0) {
            throw new CompetitionException("报名已过期");
        }
        // 判断运动员学号与名字是否匹配
        String redisName = String.valueOf(redisTemplate.opsForValue()
                .get("competition:" + "athlete_name:" + competitionId + academicNumber));
        if (redisName == null || !redisName.equals(name)) {
            throw new AthleteRegisterException("运动员姓名或学号不匹配");
        }
        // 判断运动员所属学院是否允许报名
        String redisCollege = String.valueOf(redisTemplate.opsForValue()
                .get("competition:" + "allow_college:" + competitionId));
        if (!redisCollege.contains(college)) {
            throw new CollegeException("运动员所属学院不允许报名");
        }
        // 判断运动员报名项目是否允许
        String redisSportType = String.valueOf(redisTemplate.opsForValue()
                .get("competition:" + "allow_college:" + competitionId));
        if (redisSportType.contains(sportType)) {
            throw new ActivityException("运动员选择项目在本次比赛中禁止报名");
        }
    }
}
