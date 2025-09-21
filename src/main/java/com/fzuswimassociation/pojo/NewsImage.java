package com.fzuswimassociation.pojo;

import lombok.Data;

/**
 * 新闻图片类
 *
 * @author 李泽聿
 * @since 2025-09-04 16:18
 */

@Data
public class NewsImage {
    private String id;
    byte[] imgData;
}