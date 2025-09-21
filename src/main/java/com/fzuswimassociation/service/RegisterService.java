package com.fzuswimassociation.service;

import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.enm.FzuAcademyEnum;
import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.mappers.RegisterMapper;
import com.fzuswimassociation.until.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报名业务层
 *
 * @author 李泽聿
 * @since 2025-09-10 14:07
 */

@Service
public class RegisterService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RegisterMapper registerMapper;


    /**
     * 返回redis中的允许参赛项目列表<br>
     * 具体实现见{@link ManagerService#postSignUpList}
     *
     * @param gameId 比赛UUID
     * @return 参赛项目列表
     * @author 李泽聿
     * @since 2025-09-12 08:12
     */

    public Map<String, Object> allowSportType(String gameId) {
        String allowedEvents = String.valueOf(redisTemplate.opsForValue().get(gameId));
        // 从redis中获取学院列表
        if (allowedEvents == null) {
            return Response.failed(StatusCodeEnum.COMPETITIONS_TIME_OUT, null);

        }
        // 封装返回结果
        List<Object> res = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        ActivityTypesEnum obj;
        for (String i : allowedEvents.split("&&")) {
            obj = ActivityTypesEnum.valueOf(i);
            map.put("name", obj.getName());
            map.put("enum", obj.name());
            res.add(map);
        }
        return Response.success(res);
    }

    public Map<String, Object> getCollegeDetail() {
        Map<FzuAcademyEnum, String> collegeMap = new HashMap<>();
        for (FzuAcademyEnum i : FzuAcademyEnum.values()) {
            collegeMap.put(i, i.getAcademyName());
        }
        return Response.success(collegeMap);
    }
}

