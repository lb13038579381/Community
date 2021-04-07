package com.xupt.community.dao;

import com.xupt.community.domain.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowDao {
    List<Follow> getFansByMemberId(Long memberId);
    List<Follow> getFollowersByMemberId(Long memberId);
}
