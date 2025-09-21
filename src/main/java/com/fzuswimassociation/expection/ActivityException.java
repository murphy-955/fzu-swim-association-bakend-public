package com.fzuswimassociation.expection;

/**
 * 在{@link com.fzuswimassociation.aspect.AthleteRegisterAdvice}中<br>
 * 抛出的{@link ActivityException}异常<br>
 * 用于处理运动员选择项目在本次比赛中禁止报名的情况<br>
 *
 * @author 李泽聿
 * @since 2025-09-12 12:12
 */

public class ActivityException extends RuntimeException {
    public ActivityException(String message) {
        super(message);
    }
}
