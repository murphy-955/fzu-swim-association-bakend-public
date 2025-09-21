package com.fzuswimassociation.pojo.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 查看运动员报名情况实体类
 *
 * @author 李泽聿
 * @since 2025-09-15 08:22
 */


@Data
public class PreviewRegisterInformationVo {
    @NotNull
    private String token;
    @NotNull
    private String gameId;
}
