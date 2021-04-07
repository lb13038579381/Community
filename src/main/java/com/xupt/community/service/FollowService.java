package com.xupt.community.service;

import com.xupt.community.domain.Follow;

import java.util.List;

public interface FollowService {
    List<Follow> getFansByMemberId(Long memberId);
    List<Follow> getFollersByMemberId(Long memberId);
}
