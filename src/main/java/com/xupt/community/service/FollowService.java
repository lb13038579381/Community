package com.xupt.community.service;

import com.xupt.community.domain.Follow;

import java.util.List;

public interface FollowService {
    List<Follow> myFollow(Long memberId);
    List<Follow> followedMembers(Long communityId);
    void delete(Follow follow);

    void add(Follow follow);

    Follow getByMemberIdAndCommunityId(Long memberId, Long communityId);
}
