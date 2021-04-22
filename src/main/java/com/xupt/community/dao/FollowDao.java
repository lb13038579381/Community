package com.xupt.community.dao;

import com.xupt.community.domain.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowDao {
    List<Follow> myFollow(Long memberId);
    List<Follow> followedMembers(Long communityId);
    void delete(Follow follow);

    void add(Follow follow);

    Follow getByMemberIdAndCommunityId(Follow follow);
}
