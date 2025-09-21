package com.fzuswimassociation.excel;

import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.enm.FzuAcademyEnum;
import com.fzuswimassociation.until.EnumToListUtil;
import lombok.Data;

import java.util.List;

/**
 * 导出运动员实体类
 *
 * @author 李泽聿
 * @since 2025-09-16 09:53
 */

@Data
public class ExportAthlete {
    private String competitionName;
    private String athleteName;
    private String athleteId;
    private String college;
    private List<String> registerEvent;

    public void setRegisterEvent(String dbString) {
        this.registerEvent = EnumToListUtil.activityToChineseList(dbString, ActivityTypesEnum.class);
    }

    public void setCollege(FzuAcademyEnum dbString) {
        this.college = dbString.getAcademyName();
    }
}
