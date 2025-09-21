package com.fzuswimassociation.expection;


import org.aspectj.lang.JoinPoint;

/**
 * 在{@link com.fzuswimassociation.aspect.AthleteRegisterAdvice#beforeAthleteRegister(JoinPoint)}中，<br>
 * 抛出的报名过期异常是{@link com.fzuswimassociation.expection.CompetitionException}，<br>
 *
 * @author 李泽聿
 * @since 2025-09-12 10:47
 */

public class CompetitionException extends RuntimeException {
    public CompetitionException(String message) {
        super(message);
    }
}
