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


    public Map<String, Object> getCollegeDetail() {
        Map<FzuAcademyEnum, String> collegeMap = new HashMap<>();
        for (FzuAcademyEnum i : FzuAcademyEnum.values()) {
            collegeMap.put(i, i.getAcademyName());
        }
        return Response.success(collegeMap);
    }
}

