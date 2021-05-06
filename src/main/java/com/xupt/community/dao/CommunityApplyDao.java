package com.xupt.community.dao;

import com.xupt.community.domain.CommunityApply;
import com.xupt.community.dto.CommunityApplyDto;
import com.xupt.community.dto.CommunityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/16 11:06 下午
 */
@Mapper
public interface CommunityApplyDao {
    void add(CommunityApply communityApply);
    void delete(CommunityApply communityApply);
    List<CommunityApply> getCommunityAppliesByCommunityId(Long communityId);
    List<CommunityApply> getCommunityAppliesByMemberId(Long memberId);

    CommunityApply getByMemberIdAndCommunityId(CommunityApply apply);
    
    void deleteById(Long id);
    void refuseApply(Long id);
    void adoptApply(Long id);

    CommunityApply getById(Long id);
}
