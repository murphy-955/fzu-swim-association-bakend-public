package com.fzuswimassociation.service;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.fzuswimassociation.annotation.CheckAthlete;
import com.fzuswimassociation.annotation.CheckAthleteLimit;
import com.fzuswimassociation.annotation.CheckToken;
import com.fzuswimassociation.enm.ActivityTypesEnum;
import com.fzuswimassociation.enm.StatusCodeEnum;
import com.fzuswimassociation.excel.ExportAthlete;
import com.fzuswimassociation.mappers.SportMapper;
import com.fzuswimassociation.pojo.AthleteDetail;
import com.fzuswimassociation.pojo.CompetitionPreview;
import com.fzuswimassociation.pojo.PreviewRegister;
import com.fzuswimassociation.pojo.vo.AthleteRegisterVo;
import com.fzuswimassociation.pojo.vo.PreviewRegisterInformationVo;
import com.fzuswimassociation.until.Response;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 运动员报名业务层
 *
 * @author 李泽聿
 * @since 2025-09-12 10:14
 */

@Service
public class SportService {
    @Autowired
    private SportMapper sportMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @CheckAthlete
    @CheckAthleteLimit
    public Map<String, Object> register(AthleteRegisterVo athleteRegisterVo) {
        String academicNumber = athleteRegisterVo.getAcademicNumber();
        String sportType = athleteRegisterVo.getSportType();
        String competitionId = athleteRegisterVo.getCompetitionId();
        int res;
        String types = sportMapper.getAthleteTypes(competitionId, academicNumber);
        if (types != null) {
            // 使用Set来减少重复计算和提高查找效率
            Set<String> typeSet = new HashSet<>(Arrays.asList(types.split("&&")));
            // 防止插入相同的数据
            if (typeSet.contains(sportType)) {
                return Response.failed(StatusCodeEnum.ATHLETE_CHOICE_ACTIVITY_IS_INCLUDED, null);
            }
            sportType = sportType.concat("&&").concat(types);
        }
        // 校验是否属于ActivityTypesEnum
        try {
            sportType = ActivityTypesEnum.valueOf(sportType).name();
        } catch (IllegalArgumentException e) {
            return Response.failed(StatusCodeEnum.REGISTER_FAILED, e.getMessage());
        }

        res = sportMapper.register(academicNumber, sportType, competitionId);
        if (res > 0) {
            redisTemplate.opsForValue().increment(competitionId + academicNumber + "count");
            return Response.success(null);
        }
        return Response.failed(StatusCodeEnum.REGISTER_FAILED, null);
    }

    @CheckToken
    public Map<String, Object> preview(PreviewRegisterInformationVo vo) {
        String gameId = vo.getGameId();
        PreviewRegister competition = sportMapper.preview(gameId);
        List<AthleteDetail> athleteDetail = sportMapper.getAthleteDetail(gameId);
        if (competition != null && athleteDetail != null) {
            competition.setAthleteDetail(athleteDetail);
            return Response.success(competition);
        }
        return Response.failed(StatusCodeEnum.GET_NEWS_FAILED, null);
    }

    @SneakyThrows
    @CheckToken
    public void export(PreviewRegisterInformationVo vo, HttpServletResponse response) {
        List<ExportAthlete> details = sportMapper.export(vo.getGameId());
        if (details != null) {
            System.out.println(details.getFirst().getCompetitionName());
        } else {
            System.out.println("有问题");
        }
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 设置表头
        writer.addHeaderAlias("competitionName", "比赛名称");
        writer.addHeaderAlias("athleteName", "运动员姓名");
        writer.addHeaderAlias("athleteId", "运动员学号");
        writer.addHeaderAlias("college", "学院");
        writer.addHeaderAlias("registerEvent", "报名项目");

        // 写入数据
        writer.write(details, true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        String fileName = URLEncoder.encode(details.getFirst().getCompetitionName() + ".xlsx", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + fileName);
        System.out.println(details.getFirst().getCompetitionName() + ".xlsx");

        // 将文件输出到客户端
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

    /**
     * 返回redis中的允许参赛项目列表<br>
     * 具体实现见{@link ManagerService#postSignUpList}
     *
     * @return 参赛项目列表
     * @author 李泽聿
     * @since 2025-09-12 08:12
     */
    public Map<String, Object> getGameList() {
        List<CompetitionPreview> res = sportMapper.getGameList();
        if (res == null) {
            return Response.failed(StatusCodeEnum.GET_REGISTER_COMPETITION_FAILED, null);
        }
        return Response.success(res);
    }

    public Map<String, Object> getGameInfo(String gameId) {
        String key = "competition:" + "id:" + gameId + ":activity_types:";
        System.out.println(key);
        String allowedEvents = String.valueOf(redisTemplate.opsForValue().get(key));
        // 从redis中获取允许参赛项目列表
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
}
