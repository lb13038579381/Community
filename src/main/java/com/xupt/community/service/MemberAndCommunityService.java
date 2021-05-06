package com.xupt.community.service;

import com.xupt.community.domain.MemberAndCommunity;
import com.xupt.community.dto.MemberAndCommunityDto;

import java.util.List;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/16 10:54 下午
 */
public interface MemberAndCommunityService {
    List<Long> getMemberIdsByCommunityId(Long communityId);

    Integer getTypeByCommunityIdAndMemberId(Long memberId, Long communityId);

    List<Long> getCommunityIdsByMemberId(Long memberId);

    void add(MemberAndCommunityDto dto);

    void delete(MemberAndCommunityDto dto);

    List<Long> getCommunityIdsByMemberIdAndType(Long memberId, Integer type);

    List<MemberAndCommunity> getByMemberId(Long memberId);

    MemberAndCommunity getByMemberIdAndCommunityId(Long memberId, Long communityId);
}
