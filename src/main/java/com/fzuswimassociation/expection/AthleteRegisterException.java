package com.fzuswimassociation.expection;

public class AthleteRegisterException extends RuntimeException {
    public AthleteRegisterException(String message) {
        super("运动员信息有误");
    }
}
