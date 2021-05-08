package com.xupt.community.service;

import com.xupt.community.domain.CommunityApply;
import com.xupt.community.domain.NewCommunityApply;

import java.util.List;

public interface NewCommunityApplyService {
    void add(NewCommunityApply newCommunityApply);
    List<NewCommunityApply> list();
    List<NewCommunityApply> getByMemberId(Long memberId);

    void deleteById(Long id);
    void refuseApply(Long id);
    void adoptApply(Long id);
    NewCommunityApply getById(Long id);
}
