package com.fzuswimassociation.mappers;

import com.fzuswimassociation.enm.FzuAcademyEnum;
import com.fzuswimassociation.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * 管理员持久层
 *
 * @author 李泽聿
 * @since 2025-09-01 20:07
 */

@Mapper
public interface ManagerMapper {

    Manager login(@Param("userName") String userName, @Param("password") String password);

    int deleteNews(Integer id);

    int withdrawDeletionNews(Integer id);

    int addLeader(
            @Param("name") String name,
            @Param("age") Integer age,
            @Param("introduction") String introduction,
            @Param("position") String position
    );

    int deleteLeader(Integer id);

    int addExcellentAthlete(
            @Param("athleteName") String athleteName,
            @Param("athleteAge") String athleteAge,
            @Param("athleteGrade") String athleteGrade,
            @Param("athleteIntroduction") String athleteIntroduction);

    int deleteExcellentAthlete(Integer id);

    int saveNewsContent(
            @Param("imgUrl") String imgUrl,
            @Param("data") String data,
            @Param("title") String title,
            @Param("type") String type);

    int saveNewsImg(
            @Param("imgId") String imgId,
            @Param("data") byte[] data);


    int saveVideoPreviewImg(@Param("id") String videoPreviewImgId,
                            @Param("data") byte[] videoPreviewImg);

    int saveVideo(@Param("videoId") String videoId,
                  @Param("videoData") byte[] video);

    int saveGeneralVideoInformation(@Param("videoUrl") String videoUrl,
                                    @Param("videoPreviewImgUrl") String videoPreviewImgUrl,
                                    @Param("title") String title,
                                    @Param("content") String content,
                                    @Param("type") String type);

    int insertCompetitions(@Param("id") String competitionId,
                           @Param("name") String competitionName,
                           @Param("end") LocalDate endTime,
                           @Param("events") String allowedEvents,
                           @Param("collegeLimits") int collegeActivityLimits,
                           @Param("athleteLimits") int athleteActivityLimits,
                           @Param("activityLimits") int sameActivityMaxLimit,
                           @Param("phone") String leaderPhone,
                           @Param("college") String college);


    int insertCompetitionsDetails(@Param("cid") String competitionId,
                                  @Param("did") String athleteId,
                                  @Param("name") String name,
                                  @Param("id") String academicNumber,
                                  @Param("college") FzuAcademyEnum college);
}
