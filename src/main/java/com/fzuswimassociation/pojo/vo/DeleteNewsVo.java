package com.fzuswimassociation.pojo.vo;


import com.fzuswimassociation.validator.CheckToDeleteNews;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 需要删除的新闻
 *
 * @author 李泽聿
 * @since 2025-09-01 20:52
 */

@Data
public class DeleteNewsVo {
    @NotNull(message = "id不能为空",groups = CheckToDeleteNews.class)
    private Integer id;
    @NotEmpty(message = "token不能为空",groups = CheckToDeleteNews.class)
    private String token;
}
