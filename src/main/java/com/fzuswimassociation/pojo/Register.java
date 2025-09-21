package com.fzuswimassociation.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * 报名实体类
 *
 * @author 李泽聿
 * @since 2025-09-10 15:22
 */

@Data
public class Register {
    private String competitionId;
    private String competitionName;
    private LocalDate startTime;
    private LocalDate endTime;
    private String allowedEvents;
    // 学院相同项目报名限制
    private Integer collegeActivityLimit;
    // 运动员个人报名限制
    private Integer athleteActivityLimit;
    // 相同项目最大人数限制
    private Integer sameActivityMaxLimit;
    private String detailId;
    private String athleteName;
    private String athleteId;
    private String registerEvent;
    private String college;
}
