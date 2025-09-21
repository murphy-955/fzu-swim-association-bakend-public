package com.fzuswimassociation.pojo;

import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.enm.FzuAcademyEnum;
import com.fzuswimassociation.until.EnumToListUtil;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 报名预览实体类
 *
 * @author 李泽聿
 * @since 2025-09-15 08:27
 */
@Data
public class PreviewRegister {
    private String competitionName;
    private List<AthleteDetail> athleteDetail;
    private LocalDate endTime;
    private String collegeActivityLimit;
    private String athleteActivityLimit;
    private String sameActivityMaxLimit;
    private String leaderPhone;
    private List<FzuAcademyEnum> allowedColleges;
    private List<ActivityTypesEnum> allowedEvents;


    public void setAllowedColleges(String dbColleges) {
        this.allowedColleges = EnumToListUtil.enumToList(dbColleges, FzuAcademyEnum.class);
    }

    public void setAllowedEvents(String dbEvents) {
        this.allowedEvents = EnumToListUtil.enumToList(dbEvents, ActivityTypesEnum.class);
    }
}
