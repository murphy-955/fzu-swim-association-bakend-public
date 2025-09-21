package com.fzuswimassociation.service;

import com.fzuswimassociation.annotation.CheckToken;
import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.enm.FzuAcademyEnum;
import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.mappers.ManagerMapper;
import com.fzuswimassociation.pojo.vo.*;
import com.fzuswimassociation.until.JwtUtil;
import com.fzuswimassociation.until.Response;
import com.fzuswimassociation.pojo.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 管理员业务层
 *
 * @author 李泽聿
 * @since 2025-09-01 19:20
 */

@Service
public class ManagerService {
    private static final Logger log = LoggerFactory.getLogger(ManagerService.class);
    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Map<String, Object> login(ManagerLoginVo managerLoginVo) {
        System.out.printf("user name: %s, password: %s%n", managerLoginVo.getUserName(),
                managerLoginVo.getPassword());
        Manager manager = managerMapper.login(managerLoginVo.getUserName(), managerLoginVo.getPassword());
        if (manager == null) {
            return Response.failed(StatusCodeEnum.LOGIN_FAILED, null);
        }
        String token = jwtUtil.createToken(manager.getId());
        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        return Response.success(res);
    }

    @CheckToken
    public Map<String, Object> deleteNews(DeleteNewsVo deleteNewsVo) {
        int isDeleted = managerMapper.deleteNews(deleteNewsVo.getId());
        System.out.printf("isDeleted = %d%n", isDeleted);
        if (isDeleted == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_DELETE_NEWS, null);
    }

    @CheckToken
    public Map<String, Object> withdrawDeletionNews(DeleteNewsVo deleteNewsVo) {
        int isUpdated = managerMapper.withdrawDeletionNews(deleteNewsVo.getId());
        if (isUpdated == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.WITHDRAWING_THE_DELETION_OF_THE_NEWS_FAILED, null);
    }

    @CheckToken
    public Map<String, Object> addLeader(AddLeaderVo addLeaderVo) {
        int res = managerMapper.addLeader(addLeaderVo.getName(), addLeaderVo.getAge(), addLeaderVo.getIntroduction(),
                addLeaderVo.getPosition());
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_ADD_LEADER, null);
    }

    @CheckToken
    public Map<String, Object> deleteLeader(DeleteNewsVo deleteNewsVo) {
        int res = managerMapper.deleteLeader(deleteNewsVo.getId());
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_DELETE_LEADER, null);
    }

    @CheckToken
    public Map<String, Object> addExcellentAthlete(AddExcellentAthleteVo addExcellentAthleteVo) {
        int res = managerMapper.addExcellentAthlete(addExcellentAthleteVo.getName(), addExcellentAthleteVo.getAge(),
                addExcellentAthleteVo.getGrade(), addExcellentAthleteVo.getIntroduction());
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_ADD_EXCELLENT_ATHLETE, null);
    }

    @CheckToken
    public Map<String, Object> deleteExcellentAthlete(DeleteNewsVo deleteNewsVo) {
        int res = managerMapper.deleteExcellentAthlete(deleteNewsVo.getId());
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_DELETE_EXCELLENT_ATHLETE, null);
    }

    @CheckToken
    public Map<String, Object> uploadNews(UploadNewsVo uploadNewsVo) {
        List<Content> contentList = uploadNewsVo.getContent();
        // 雪花算法获取imgId
        String imgId = UUID.randomUUID().toString().replace("-", "");
        String imgUrl = "/activity/getNewsImage?id=%s".formatted(imgId);
        int res = 0;
        for (Content c : contentList) {
            if (c.getType().equals("text")) {
                // 保存文本内容到数据库
                res = managerMapper.saveNewsContent(imgUrl, c.getData().toString(),
                        uploadNewsVo.getTitle(), c.getType());
            } else {
                try {
                    byte[] imgData = Base64.getDecoder().decode(c.getData().toString());
                    res = managerMapper.saveNewsImg(imgId, imgData);
                } catch (Exception e) {
                    log.error("上传图片失败", e);
                }
            }
        }
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_UPLOAD_NEWS, null);
    }

    @CheckToken
    public Map<String, Object> uploadVideo(UploadVideoVo uploadVideoVo) {
        // 获取videoId
        String videoId = UUID.randomUUID().toString().replace("-", "");
        String videoUrl = "/activity/getNewsVideo?id=%s".formatted(videoId);
        String videoPreviewImgId, videoPreviewImgUrl = "";
        int imgRes = 0;
        if (uploadVideoVo.getVideoPreviewImg() != null) {
            System.out.println(uploadVideoVo.getVideoPreviewImg().length);
            videoPreviewImgId = UUID.randomUUID().toString().replace("-", "");
            videoPreviewImgUrl = "/activity/getNewsImage?id=%s".formatted(videoPreviewImgId);
            imgRes = managerMapper.saveVideoPreviewImg(videoPreviewImgId, uploadVideoVo.getVideoPreviewImg());
        }
        int videoRes = managerMapper.saveVideo(videoId, uploadVideoVo.getVideo());
        int newsRes = managerMapper.saveGeneralVideoInformation(videoUrl, videoPreviewImgUrl, uploadVideoVo.getTitle(),
                uploadVideoVo.getContent(), "video");
        if (videoRes == 1 && newsRes == 1 && imgRes == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_UPLOAD_VIDEO, null);
    }

    @CheckToken
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> postSignUpList(ManagerUploadRegisterVo vo) {
        // 生成比赛uuid
        String competitionId = UUID.randomUUID().toString().replace("-", "");
        // 运送员id
        String athleteId;
        String allowedEvents = "";
        String college = "";
        LocalDate startDate = vo.getStartTime();
        LocalDate endDate = vo.getEndTime();
        int res;
        int athleteLimit = vo.getAthleteActivityLimits();
        if (startDate == null) {
            startDate = LocalDate.now();
        }
        // 计算过期时间
        long expireTime = vo.getEndTime().toEpochDay() - startDate.toEpochDay();
        expireTime = expireTime * 24 * 60 * 60;
        for (ActivityTypesEnum i : vo.getActivityTypes()) {
            allowedEvents = allowedEvents.concat(String.valueOf(i));
            allowedEvents = allowedEvents.concat("&&");
        }
        for (FzuAcademyEnum i : vo.getColleges()) {
            college = college.concat(String.valueOf(i));
            college = college.concat("&&");
        }
        try {
            res = managerMapper.insertCompetitions(competitionId, vo.getCompetitionName(),
                    endDate, allowedEvents, vo.getCollegeActivityLimits(),
                    athleteLimit, vo.getSameActivityMaxLimit(), vo.getLeaderPhone(), college);
            if (res == 1) {
                // 将比赛uuid作为key，比赛允许报名项目作为value存入redis，并设置过期时间
                // 用于比赛报名时判断运动员勾选项目是否允许报名
                redisTemplate.opsForValue().set("competition:" + "id:" + competitionId, allowedEvents,
                        expireTime, TimeUnit.SECONDS);
                // 将比赛uuid+college作为key，比赛允许报名的学院作为value存入redis
                // 用于比赛报名时判断运动员所属学院是否允许报名
                redisTemplate.opsForValue().set("competition:" + "allow_college:" + competitionId, college,
                        expireTime, TimeUnit.SECONDS);
                // 将比赛uuid+athleteLimit作为key，比赛允许一个运动员报名的最大次数作为value存入redis
                // 用于校验运动员是否重复保项
                redisTemplate.opsForValue().set("competition:" + "athlete_limit:" + competitionId, athleteLimit,
                        expireTime, TimeUnit.SECONDS);
                // 保存比赛名称
                redisTemplate.opsForValue().set("competition:" + "name:" + competitionId, vo.getCompetitionName(),
                        expireTime, TimeUnit.SECONDS);
            }

            for (AthleteList i : vo.getAthleteLists()) {
                athleteId = UUID.randomUUID().toString().replace("-", "");
                res = managerMapper.insertCompetitionsDetails(competitionId, athleteId, i.getName(), i.getAcademicNumber(),
                        i.getCollege());
                if (res == 1) {
                    // 将比赛uuid+运动员学号+name作为key，运动员姓名作为value存入redis
                    // 用于比赛报名时判断运动员是否允许报名
                    redisTemplate.opsForValue().set("competition:" + "athlete_name:" + competitionId + i.getAcademicNumber(), i.getName(),
                            expireTime, TimeUnit.SECONDS);
                    // 将比赛uuid+运动员学号+count作为key，运动员报名次数作为value存入redis
                    // 用于比赛报名时判断运动员是否已报名
                    redisTemplate.opsForValue().set("competition:" + "register_count:" + competitionId + i.getAcademicNumber(),
                            0, expireTime, TimeUnit.SECONDS);
                }
            }
            return Response.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failed(StatusCodeEnum.FAILED_TO_UPLOAD_SIGN_UP_LIST, null);
        }
    }


}
