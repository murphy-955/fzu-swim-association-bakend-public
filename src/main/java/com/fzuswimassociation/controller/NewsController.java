package com.fzuswimassociation.controller;

import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 显示福州大学游泳协会的主要活动信息(新闻)
 *
 * @author 李泽聿
 * @since 2025-08-28 08:50
 */

@RestController
@RequestMapping("activity")
@CrossOrigin
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("getNewsList")
    public Map<String, Object> getNewsList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        System.out.println(StatusCodeEnum.SUCCESS.getMessage());
        return newsService.getNewsList(page, limit);
    }

    @GetMapping("getDetailNews")
    public Map<String, Object> getDetailNews(@RequestParam(value = "id") Integer id) {
        return newsService.getDetailNews(id);
    }

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "uuid") String id) {
        return newsService.getImage(id);
    }

    @GetMapping("getVideo")
    public ResponseEntity<byte[]> getVideo(@RequestParam(value = "uuid") String id) {
        return newsService.getVideo(id);
    }

}

