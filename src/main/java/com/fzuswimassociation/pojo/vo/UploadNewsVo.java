package com.fzuswimassociation.pojo.vo;

import com.fzuswimassociation.validator.CheckUploadNews;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 管理员上传新闻的json
 *
 * @author 李泽聿
 * @since 2025-09-03 13:02
 */

@Data
public class UploadNewsVo {
    @NotNull(groups = {CheckUploadNews.class})
    private String token;
    @NotNull(groups = {CheckUploadNews.class})
    private String title;
    @NotNull(groups = {CheckUploadNews.class})
    private List<Content> content;
}

