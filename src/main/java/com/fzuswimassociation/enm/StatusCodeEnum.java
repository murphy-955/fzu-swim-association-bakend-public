package com.fzuswimassociation.enm;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态码枚举
 *
 * @author 李泽聿
 * @since 2025-08-28 08:54
 */

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    SUCCESS(200, "成功"),
    GET_NEWS_FAILED(420, "获取最新闻据失败"),
    GET_DETAIL_NEWS_FAILED(421, "获取新闻详情失败"),
    FAILED_TO_GET_THE_RELEVANT_LEAD_DETAILS(422, "获取相关领导详情失败"),
    EXCELLENT_ATHLETES_DON_T_EXIST(423, "优秀运动员不存在"),
    LOGIN_FAILED(424, "登录失败"),
    TOKEN_EXPIRED(425, "Token错误"),
    FAILED_TO_DELETE_NEWS(426, "删除新闻失败"),
    WITHDRAWING_THE_DELETION_OF_THE_NEWS_FAILED(427, "撤销删除新闻失败"),
    FAILED_TO_ADD_LEADER(428, "添加领导失败"),
    FAILED_TO_DELETE_LEADER(429, "删除领导失败"),
    FAILED_TO_ADD_EXCELLENT_ATHLETE(430, "添加优秀运动员失败"),
    FAILED_TO_DELETE_EXCELLENT_ATHLETE(431, "删除优秀运动员失败"),
    FAILED_TO_GET_THE_PICTURE(432, "获取图片失败"),
    FAILED_TO_UPLOAD_NEWS(433, "上传新闻失败"),
    FAILED_TO_UPLOAD_VIDEO(434, "上传视频失败"),
    FAILED_TO_GET_THE_NEWS_VIDEO(435, "获取新闻视频失败"),
    FAILED_TO_UPLOAD_SIGN_UP_LIST(436, "上传报名信息失败"),
    COMPETITIONS_TIME_OUT(437, "已截至报名"),
    ATHLETES_DON_T_MATCH(438, "运动员姓名或学号不匹配"),
    THE_ATHLETE_PROGRAM_DOES_NOT_EXIST(439, "运动员所属学院不允许报名"),
    ATHLETE_CHOICE_ACTIVITY_NOT_ALLOWED(440, "运动员选择的活动不允许报名"),
    ATHLETE_REGISTER_OVER_LIMIT(441, "运动员报名次数已达上限"),
    REGISTER_FAILED(442, "报名失败"),
    ATHLETE_CHOICE_ACTIVITY_IS_INCLUDED(443, "运动员不能报名相同的项目"),
    REQUESTS_ARE_TOO_FREQUENT(444,"请求过于频繁，请稍后再试"),
    GET_REGISTER_COMPETITION_FAILED(445, "获取报名比赛失败");

    private final Integer statusCode;
    private final String message;
}
