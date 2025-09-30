package com.fzuswimassociation.service;

import com.fzuswimassociation.annotation.CheckToken;
import com.fzuswimassociation.annotation.ManagerLimitLogin;
import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.enm.FzuAcademyEnum;
import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.mappers.ManagerMapper;
import com.fzuswimassociation.pojo.vo.*;
import com.fzuswimassociation.strategy.login.LoginFactory;
import com.fzuswimassociation.until.ContentTypeToFileSuffixNameUtil;
import com.fzuswimassociation.until.JwtUtil;
import com.fzuswimassociation.until.Response;
import com.fzuswimassociation.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
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
    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private LoginFactory loginFactory;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ManagerLimitLogin
    public Map<String, Object> login(ManagerLoginVo vo, String requestIp) {
        vo.setIp(requestIp);
        return loginFactory.login(vo.getLoginType()).login(vo);
    }

    @CheckToken
    public Map<String, Object> deleteNews(DeleteNewsVo deleteNewsVo) {
        int isDeleted = managerMapper.deleteNews(deleteNewsVo.getId());
        if (isDeleted == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_DELETE_NEWS, null);
    }

    @CheckToken
    public Map<String, Object> withdrawNews(DeleteNewsVo deleteNewsVo) {
        int isUpdated = managerMapper.withdrawNews(deleteNewsVo.getId());
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
    public Map<String, Object> addExcellence(AddExcellentAthleteVo addExcellentAthleteVo) {
        int res = managerMapper.addExcellence(addExcellentAthleteVo.getName(), addExcellentAthleteVo.getAge(),
                addExcellentAthleteVo.getGrade(), addExcellentAthleteVo.getIntroduction());
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_ADD_EXCELLENT_ATHLETE, null);
    }

    @CheckToken
    public Map<String, Object> deleteExcellence(DeleteNewsVo deleteNewsVo) {
        int res = managerMapper.deleteExcellence(deleteNewsVo.getId());
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_DELETE_EXCELLENT_ATHLETE, null);
    }

    @CheckToken
    public Map<String, Object> uploadImage(UploadImageVo vo) {
        byte[] bytes;
        try {
            bytes = vo.getImg().getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 雪花算法获取imgId
        String imgId = UUID.randomUUID().toString().replace("-", "");
        String imgType = ContentTypeToFileSuffixNameUtil.getSuffixName(vo.getImg().getContentType());
        String imgUrl = "/activity/getNewsImage?id=%s&type=%s".formatted(imgId, imgType);

        try {
            Files.createDirectories(Path.of("images/"));
            Files.write(Path.of("images/" + imgId + imgType), bytes);
            Map<String, Object> params = new HashMap<>();
            params.put("url", imgUrl);
            return Response.success(params);
        } catch (IOException e) {
            return Response.failed(StatusCodeEnum.FAILED_TO_UPLOAD_NEWS, null);
        }
    }

    @CheckToken
    public Map<String, Object> uploadVideo(UploadVideoVo vo) {
        byte[] bytes;
        try {
            bytes = vo.getVideo().getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 获取videoId
        String videoId = UUID.randomUUID().toString().replace("-", "");
        String videoType = ContentTypeToFileSuffixNameUtil.getSuffixName(vo.getVideo().getContentType());
        String videoUrl = "/activity/getNewsVideo?id=%s&type=%s".formatted(videoId, videoType);
        try {
            Files.createDirectories(Path.of("videos/"));
            Files.write(Path.of("videos/" + videoId + videoType), bytes);
            Map<String, Object> params = new HashMap<>();
            params.put("videoUrl", videoUrl);
            return Response.success(params);
        } catch (Exception e) {
            return Response.failed(StatusCodeEnum.FAILED_TO_UPLOAD_VIDEO, null);
        }
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
                redisTemplate.opsForValue().set("competition:" + "id:" + competitionId + ":activity_types:", allowedEvents,
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


    @CheckToken
    public Map<String, Object> getAdminList(GetManagerListVo vo) {
        List<Manager> managers = managerMapper.getAdminList();
        return Response.success(managers);
    }

    @CheckToken
    public Map<String, Object> deleteAdmin(DeleteAdminVo vo) {
        int res = managerMapper.deleteAdmin(vo.getAdminId());
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_DELETE_ADMIN, null);
    }

    @CheckToken
    public Map<String, Object> addAdmin(AddAdminVo vo) {
        int res = managerMapper.addAdmin(vo.getAdminName(), vo.getPassword());
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_ADD_ADMIN, null);
    }

    @CheckToken
    public Map<String, Object> updateAdmin(UpdateAdminVo vo) {
        int res = managerMapper.updateAdmin(vo.getId(), vo.getAdminName(), vo.getPassword());
        if (res == 1) {
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_UPDATE_ADMIN, null);
    }

    @CheckToken
    public Map<String, Object> uploadNews(UploadNewsVo vo) {
        String tittle = vo.getTitle();
        String content = "";
        String img_url = "";
        String video_url = "";
        for (Content i : vo.getContent()) {
            if (i.getType().equals("image")&&i.getData()!=null) {
                img_url = img_url.concat((String) i.getData());
                img_url = img_url.concat("&&");
            }
            if (i.getType().equals("video")&&i.getData()!=null) {
                video_url = video_url.concat((String) i.getData());
            }
            if (i.getType().equals("text")) {
                content = content.concat((String) i.getData());
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("title", vo.getTitle());
        params.put("type", "text");
        params.put("content", content);
        params.put("imgUrl", img_url);
        params.put("videoUrl", video_url);

        int res = managerMapper.uploadNews(params);

        BigInteger id = (BigInteger) params.get("id");
        if (res == 1) {
            return Response.success(id);
        }
        return Response.failed(StatusCodeEnum.FAILED_TO_UPLOAD_NEWS, null);
    }
}
