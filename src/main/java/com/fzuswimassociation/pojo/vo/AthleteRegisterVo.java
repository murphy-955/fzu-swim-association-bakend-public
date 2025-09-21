package com.fzuswimassociation.pojo.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 运动员报名实体类
 *
 * @author 李泽聿
 * @since 2025-09-12 10:11
 */

@Data
public class AthleteRegisterVo {
    @NotNull
    private String name;
    @NotNull
    private String academicNumber;
    @NotNull
    private String sportType;
    @NotNull
    private String college;
    @NotNull
    private String competitionId;
}
