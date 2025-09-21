package com.fzuswimassociation.pojo.vo;

import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.enm.FzuAcademyEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 管理员上传报名信息的JSON实体类<br>
 * {@code activityTypes} 运动员报名的类型在 {@link ActivityTypesEnum} 中定义<br>
 * {@code college} 运动员报名的学院在 {@link FzuAcademyEnum} 中定义<br>
 *
 * @author 李泽聿
 * @since 2025-09-10 14:13
 */

@Data
public class ManagerUploadRegisterVo {
    @NotNull(message = "token不能为空")
    private String token;
    @NotNull(message = "运动员列表不能为空")
    private List<AthleteList> athleteLists;
    @NotNull(message = "允许参赛项目不能为空")
    private List<ActivityTypesEnum> activityTypes;
    private List<FzuAcademyEnum> colleges;
    private int collegeActivityLimits;
    @NotNull(message = "运动员最多允许报名数量的不能为空")
    private int athleteActivityLimits;
    private int sameActivityMaxLimit;
    @NotNull(message = "比赛名称不能为空")
    private String competitionName;
    private String leaderPhone;
    private LocalDate startTime;
    @NotNull(message = "结束日期不能为空")
    @Future(message = "结束日期必须是未来的日期")
    private LocalDate endTime;
}
