package com.fzuswimassociation.mappers;

import com.fzuswimassociation.pojo.Register;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报名持久层
 *
 * @author 李泽聿
 * @since 2025-09-10 14:35
 */

@Mapper
public interface RegisterMapper {

    Register getCollegeDetail(String gameId);
}
