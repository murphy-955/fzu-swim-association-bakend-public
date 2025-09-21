package com.fzuswimassociation.pojo.vo;

import com.fzuswimassociation.validator.CheckUploadNews;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 上传新闻的
 *
 * @author 李泽聿
 * @since 2025-09-03 13:26
 */

@Data
public class Content {
    @Pattern(regexp = "^(image|text$)",message = "传入的type类型只能是image或text",groups = {CheckUploadNews.class})
    private String type;
    private Object data;
}
