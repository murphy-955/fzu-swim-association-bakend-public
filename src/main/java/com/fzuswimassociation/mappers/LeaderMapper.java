package com.fzuswimassociation.mappers;

import com.fzuswimassociation.pojo.Leader;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 领导展示Mapper
 *
 * @author 李泽聿
 * @since 2025-08-28 10:32
 */

@Mapper
public interface LeaderMapper {
    List<Leader> getLeadersList();

    Leader getLeaderDetail(Integer id);
}
