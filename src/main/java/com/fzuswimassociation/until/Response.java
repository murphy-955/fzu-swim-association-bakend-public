package com.fzuswimassociation.until;

import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.pojo.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回json数据
 *
 * @author 李泽聿
 * @since 2025-08-28 08:53
 */


public class Response {

    public static Map<String, Object> success() {
        Map<String, Object> res = new HashMap<>();
        res.put("statusCode", StatusCodeEnum.SUCCESS.getStatusCode());
        res.put("message", StatusCodeEnum.SUCCESS.getMessage());
        return res;
    }

    public static Map<String, Object> success(Object data) {
        Map<String, Object> res = success();
        res.put("data", data);
        return res;
    }

    public static Map<String, Object> failed(StatusCodeEnum statusCodeEnum) {
        Map<String, Object> res = new HashMap<>();
        res.put("statusCode", statusCodeEnum.getStatusCode());
        res.put("message", statusCodeEnum.getMessage());
        return res;
    }

    public static Map<String, Object> failed(StatusCodeEnum statusCodeEnum, Object data) {
        Map<String, Object> res = failed(statusCodeEnum);
        res.put("data", data);
        return res;
    }

    // 吐槽一下新闻详情页的json格式
    // 太恶心了
    public static Map<String, Object> sendDetailNews(News news) {
        Map<String, Object> res = success();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> textData = new HashMap<>();
        Map<String, Object> imgData = new HashMap<>();
        Map<String, Object> videoData = new HashMap<>();
        List<Map<String, Object>> content = new ArrayList<>();
        // 添加新闻数据
        textData.put("type", "text");
        textData.put("data",news.getContent());
        textData.put("view",news.getView());
        imgData.put("type", "img");
        videoData.put("type", "video");
        // 添加图片数据
        if (news.getImgUrl()!= null) {
            imgData.put("url", news.getImgUrl());
        } else {
            imgData.put("url",null);
        }

        // 添加视频数据
        if (news.getVideoUrl()!= null) {
            videoData.put("url", news.getVideoUrl());
            videoData.put("preview",news.getVideoPreviewImgUrl());
            videoData.put("view",news.getView());
            imgData.put("url",news.getVideoPreviewImgUrl());
        } else {
            videoData.put("url",null);
            videoData.put("preview",null);
            videoData.put("view",null);
        }

        content.add(textData);
        content.add(imgData);
        content.add(videoData);

        data.put("title", news.getTitle());
        data.put("id", news.getId());
        data.put("time", news.getPublishTime());
        data.put("content", content);
        data.put("view", news.getView());
        res.put("data",data);

        return res;
    }
}
