package com.fzuswimassociation.pojo.vo;

import com.fzuswimassociation.validator.AddExcellentAthlete;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 新增优秀运动员实体类
 *
 * @author 李泽聿
 * @since 2025-09-02 16:58
 */

@Data
public class AddExcellentAthleteVo {
    @NotNull(message = "token不能为空",groups = AddExcellentAthlete.class)
    private String token;
    private String name;
    private String age;
    private String grade;
    private String introduction;
}
