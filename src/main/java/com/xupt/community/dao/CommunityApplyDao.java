package com.xupt.community.dao;

import com.xupt.community.dto.CommunityApplyDto;
import com.xupt.community.dto.CommunityDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/16 11:06 下午
 */
@Mapper
public interface CommunityApplyDao {
    void add(CommunityApplyDto dto);
    void delete(CommunityDto dto);
    List
}
