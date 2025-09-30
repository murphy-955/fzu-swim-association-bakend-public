package com.fzuswimassociation.service;

import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.mappers.NewsMapper;
import com.fzuswimassociation.pojo.News;
import com.fzuswimassociation.pojo.NewsVideo;
import com.fzuswimassociation.until.ContentTypeToFileSuffixNameUtil;
import com.fzuswimassociation.until.ReplacePublishFiguresUtil;
import com.fzuswimassociation.until.Response;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 显示福州大学游泳协会的主要活动信息(新闻)的业务层
 *
 * @author 李泽聿
 * @since 2025-08-28 09:13
 */

@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ReplacePublishFiguresUtil replaceUtil;

    /**
     * 获取新闻列表
     *
     * @param page  页码，默认值为1
     * @param limit 每页数量，默认值为10
     * @return 1. 成功返回{@link StatusCodeEnum#SUCCESS}<br>
     * 2. 失败返回{@link StatusCodeEnum#GET_NEWS_FAILED}
     * @author 李泽聿
     * @since 2025-08-28 09:47
     */

    public Map<String, Object> getNewsList(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<News> newsList = newsMapper.getNewsList();
        PageInfo<News> pageInfo = new PageInfo<>(newsList);

        if (page > pageInfo.getPages() || page < 1) {
            return Response.failed(StatusCodeEnum.GET_NEWS_FAILED, null);
        }
        return Response.success(newsList);
    }

    /**
     * 获取新闻详情页
     *
     * @param id 新闻id
     * @return 1. 成功返回{@link StatusCodeEnum#SUCCESS}，注意新闻中的公共人物信息已经替换为包含了a标签的形式<br>
     * 相关领导格式为:<br>
     * {@code <a href="/excellentAthletes/getExcellentAthletesById?id=%s">%s</a>} <br>
     * 优秀运动员格式为:<br>
     * {@code <a href="/leaders/getLeaderById?id=%s">%s</a>}<br><br>
     * 2. 失败返回{@link StatusCodeEnum#GET_DETAIL_NEWS_FAILED}
     * @author 李泽聿
     * @since 2025-08-28 11:07
     */

    public Map<String, Object> getNewsDetail(Integer id) {
        News detailNews = newsMapper.getDetailNews(id);
        if (detailNews == null || newsMapper.updateNewsView(id) == 0) {
            return Response.failed(StatusCodeEnum.GET_DETAIL_NEWS_FAILED, new ArrayList<>());
        }
        String text = detailNews.getContent();
        String replacedText = replaceUtil.replaceContent(text);
        detailNews.setContent(replacedText);
        return Response.sendDetailNews(detailNews);
    }

    /**
     * 将图片以Stream形式返回，并设置相应的Content为image/jpeg<br>
     * 注意：视频的预览图也调用这个接口
     *
     * @param id 图片UUid
     * @return 图片的Stream形式
     * @author 李泽聿
     * @since 2025-09-07 08:12
     */

    public ResponseEntity<byte[]> getImage(String id, String type) {
        String path = "imgs/" + id + "." + type;

        try (FileInputStream imgByte = new FileInputStream(path)) {
            BufferedInputStream stream = new BufferedInputStream(imgByte);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", ContentTypeToFileSuffixNameUtil.getContentType(type));
            return ResponseEntity.ok().headers(headers)
                    .body(stream.readAllBytes());
        } catch (IOException e) {
            return ResponseEntity
                    .status(StatusCodeEnum.FAILED_TO_GET_THE_PICTURE.getStatusCode())
                    .body(null);
        }
    }

    /**
     * 将视频以Stream形式返回，并设置相应的Content为video/mp4
     *
     * @param id 视频UUID
     * @return 视频的Stream形式
     * @author 李泽聿
     * @since 2025-09-07 08:13
     */

    public ResponseEntity<byte[]> getVideo(String id, String type) {
        String path = "videos/" + id + "." + type;
        try (FileInputStream inputStream = new FileInputStream(path);
             BufferedInputStream stream = new BufferedInputStream(inputStream)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", ContentTypeToFileSuffixNameUtil.getContentType(type));
            return ResponseEntity.ok().headers(headers)
                    .body(stream.readAllBytes());
        } catch (IOException e) {
            return ResponseEntity
                    .status(StatusCodeEnum.FAILED_TO_GET_THE_NEWS_VIDEO.getStatusCode()).body(null);
        }

    }
}
