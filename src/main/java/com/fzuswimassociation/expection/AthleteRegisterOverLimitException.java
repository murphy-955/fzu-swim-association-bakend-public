package com.fzuswimassociation.expection;

/**
 * 运动员报名数量超过比赛规定
 * @author 李泽聿
 * @since 2025-09-13 09:27
 */

public class AthleteRegisterOverLimitException extends RuntimeException {
    public AthleteRegisterOverLimitException(String message) {
        super(message);
    }
}
