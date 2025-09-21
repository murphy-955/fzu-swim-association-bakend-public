package com.fzuswimassociation.expection;

/**
 * {@link com.fzuswimassociation.aspect.AthleteRegisterAdvice}中<br>
 * 抛出的{@link CollegeException}异常，用于处理运动员学院不允许报名的异常。
 *
 * @author 李泽聿
 * @since 2025-09-12 11:21
 */

public class CollegeException extends RuntimeException {
    public CollegeException(String message) {
        super(message);
    }
}
