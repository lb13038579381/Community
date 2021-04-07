package com.xupt.community.dao;

import com.xupt.community.dto.MemberAndCommunityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/16 10:38 下午
 */
@Mapper
public interface MemberAndCommunityDao {
    List<Long> getMemberIdsByCommunityId(Long communityId);

    Integer getTypeByCommunityIdAndMemberId(Map<String,Object> params);

    List<Long> getCommunityIdsByMemberId(Long memberId);

    void add(MemberAndCommunityDto dto);

    void delete(MemberAndCommunityDto dto);

    List<Long> getCommunityIdsByMemberIdAndType(Map<String,Object> params);

}
