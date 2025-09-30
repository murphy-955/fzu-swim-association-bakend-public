package com.fzuswimassociation.mappers;

import com.fzuswimassociation.excel.ExportAthlete;
import com.fzuswimassociation.pojo.AthleteDetail;
import com.fzuswimassociation.pojo.CompetitionPreview;
import com.fzuswimassociation.pojo.PreviewRegister;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 运动员报名持久层
 *
 * @author 李泽聿
 * @since 2025-09-13 08:34
 */
@Mapper
public interface SportMapper {
    int register(
            @Param("athleteId") String academicNumber,
            @Param("type") String sportType,
            @Param("competitionId") String competitionId
    );

    String getAthleteTypes(
            @Param("competitionId") String competitionId,
            @Param("athleteId") String academicNumber
    );

    PreviewRegister preview(String gameId);

    List<AthleteDetail> getAthleteDetail(String gameId);

    List<ExportAthlete> export(@NotNull String gameId);

    List<CompetitionPreview> getGameList();
}
