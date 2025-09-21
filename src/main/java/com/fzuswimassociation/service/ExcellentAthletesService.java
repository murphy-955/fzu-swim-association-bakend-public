package com.fzuswimassociation.service;

import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.mappers.ExcellentAthletesMapper;
import com.fzuswimassociation.pojo.ExcellentAthletes;
import com.fzuswimassociation.until.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 优秀运动员业务层
 *
 * @author 李泽聿
 * @since 2025-09-01 19:08
 */

@Service
public class ExcellentAthletesService {
    @Autowired
    private ExcellentAthletesMapper excellentAthletesMapper;

    public Map<String, Object> getExcellentAthletesList() {
        List<ExcellentAthletes> excellentAthletesList = excellentAthletesMapper.getExcellentAthletesList();
        return Response.success(excellentAthletesList);
    }

    public Map<String, Object> getExcellentAthleteDetail(Integer id) {
        ExcellentAthletes excellentAthlete = excellentAthletesMapper.getExcellentAthleteDetail(id);
        if (excellentAthlete == null) {
            return Response.failed(StatusCodeEnum.EXCELLENT_ATHLETES_DON_T_EXIST,null);
        }
        return Response.success(excellentAthlete);
    }
}
