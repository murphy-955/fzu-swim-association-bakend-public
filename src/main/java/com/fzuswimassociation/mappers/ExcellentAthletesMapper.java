package com.fzuswimassociation.mappers;

import com.fzuswimassociation.pojo.ExcellentAthletes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 优秀运动员接口
 *
 * @author 李泽聿
 * @since 2025-08-28 10:19
 */
@Mapper
public interface ExcellentAthletesMapper {

    List<ExcellentAthletes> getExcellentAthletesList();

    ExcellentAthletes getExcellentAthleteDetail(Integer id);
}
