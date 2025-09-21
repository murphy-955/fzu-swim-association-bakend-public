package com.fzuswimassociation.mappers;

import com.fzuswimassociation.pojo.News;
import com.fzuswimassociation.pojo.NewsImage;
import com.fzuswimassociation.pojo.NewsVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李泽聿
 * @since 2025-08-28 09:18
 */


@Mapper
public interface NewsMapper {
    List<News> getNewsList();

    News getDetailNews(Integer id);

    NewsImage getImage(@Param("id") String id);

    NewsVideo getVideo(String id);

    int updateNewsView(@Param("id") Integer id);
}
