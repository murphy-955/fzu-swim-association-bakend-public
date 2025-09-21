package com.fzuswimassociation.config;

import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.expection.*;
import com.fzuswimassociation.until.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.CommunicationException;
import java.util.Map;

/**
 * 全局异常处理器，用来处理Token过期异常
 *
 * @author 李泽聿
 * @since 2025-09-02 16:45
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public Map<String, Object> handleTokenExpiredException() {
        return Response.failed(StatusCodeEnum.TOKEN_EXPIRED,null);
    }

    @ExceptionHandler(CommunicationException.class)
    @ResponseBody
    public Map<String, Object> handleCommunicationException() {
        return Response.failed(StatusCodeEnum.COMPETITIONS_TIME_OUT,null);
    }

    @ExceptionHandler(AthleteRegisterException.class)
    @ResponseBody
    public Map<String, Object> handleAthleteRegisterException(AthleteRegisterException e) {
        return Response.failed(StatusCodeEnum.ATHLETES_DON_T_MATCH, null);
    }

    @ExceptionHandler(CollegeException.class)
    @ResponseBody
    public Map<String, Object> handleCollegeException(CollegeException e) {
        return Response.failed(StatusCodeEnum.THE_ATHLETE_PROGRAM_DOES_NOT_EXIST, null);
    }

    @ExceptionHandler(ActivityException.class)
    @ResponseBody
    public Map<String, Object> handleActivityException(ActivityException e) {
        return Response.failed(StatusCodeEnum.ATHLETE_CHOICE_ACTIVITY_NOT_ALLOWED, null);
    }

    @ExceptionHandler(AthleteRegisterOverLimitException.class)
    @ResponseBody
    public Map<String, Object> handleAthleteRegisterOverLimitException(AthleteRegisterOverLimitException e) {
        return Response.failed(StatusCodeEnum.ATHLETE_REGISTER_OVER_LIMIT, null);
    }
}
