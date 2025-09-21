package com.fzuswimassociation.pojo;

import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.enm.FzuAcademyEnum;
import com.fzuswimassociation.until.EnumToListUtil;
import lombok.Data;

import java.util.List;

/**
 * 每一个运动员详情实体类
 *
 * @author 李泽聿
 * @since 2025-09-15 08:32
 */

@Data
public class AthleteDetail {
    private String athleteName;
    private String athleteId;
    private FzuAcademyEnum college;
    private List<ActivityTypesEnum> registerEvent;

    public void setRegisterEvent(String dbEvents) {
        this.registerEvent = EnumToListUtil.enumToList(dbEvents, ActivityTypesEnum.class);
    }
}
