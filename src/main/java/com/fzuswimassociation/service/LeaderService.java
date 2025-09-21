package com.fzuswimassociation.service;

import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.mappers.LeaderMapper;
import com.fzuswimassociation.pojo.Leader;
import com.fzuswimassociation.until.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 领导服务类
 *
 * @author 李泽聿
 * @since 2025-09-01 18:52
 */

@Service
public class LeaderService {
    @Autowired
    private LeaderMapper leaderMapper;

    public Map<String, Object> getLeaderList() {
        List<Leader> leadersList = leaderMapper.getLeadersList();
        return Response.success(leadersList);
    }

    public Map<String, Object> getLeaderDetail(Integer id) {
        Leader leader = leaderMapper.getLeaderDetail(id);
        if (leader == null) {
            return Response.failed(StatusCodeEnum.FAILED_TO_GET_THE_RELEVANT_LEAD_DETAILS,null);
        }
        return Response.success(leader);
    }
}
