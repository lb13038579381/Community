package com.xupt.community.service;

import com.xupt.community.domain.CommunityApply;
import com.xupt.community.dto.CommunityApplyDto;
import com.xupt.community.dto.CommunityDto;

import java.util.List;

public interface CommunityApplyService {
    void add(CommunityApply communityApply);
    void delete(CommunityApply communityApply);
    List<CommunityApply> getCommunityAppliesByCommunityId(Long communityId);
    List<CommunityApply> getCommunityAppliesByMemberId(Long memberId);

    CommunityApply getByMemberIdAndCommunityId(Long memberId, Long communityId);

    void deleteById(Long id);
    void refuseApply(Long id);
    void adoptApply(Long id);

    CommunityApply getById(Long id);
}
