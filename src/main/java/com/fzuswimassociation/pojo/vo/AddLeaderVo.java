package com.fzuswimassociation.pojo.vo;

import com.fzuswimassociation.validator.AddLeaderGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

/**
 * 新增领导
 *
 * @author 李泽聿
 * @since 2025-09-02 13:30
 */

@Data
public class AddLeaderVo {
    @NotNull(message = "token不能为空",groups = {AddLeaderGroup.class})
    private String token;
    private String name;
    private Integer age;
    private String introduction;
    private String position;
}
