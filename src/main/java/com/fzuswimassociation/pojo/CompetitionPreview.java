package com.fzuswimassociation.pojo;

import lombok.Data;

/**
 * 允许报名的比赛（没有吃过报名过期时间的比赛）实体类
 *
 * @author 李泽聿
 * @since 2025-09-20 17:21
 */

@Data
public class CompetitionPreview {
    private String competitionId;
    private String competitionName;
}
