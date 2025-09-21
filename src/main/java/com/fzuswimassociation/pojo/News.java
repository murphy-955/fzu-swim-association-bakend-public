package com.fzuswimassociation.pojo;

import lombok.Data;

/**
 * 新闻实体类
 *
 * @author 李泽聿
 * @since 2025-08-28 09:26
 */

@Data
public class News {
    private String id;


    private String title;


    private String content;


    private String view;


    private String imgUrl;


    private String videoUrl;


    private String publishTime;

    private String type;

    private String videoPreviewImgUrl;

    private String deleted;
}
