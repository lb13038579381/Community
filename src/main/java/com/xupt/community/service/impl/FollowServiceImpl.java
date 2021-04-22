package com.xupt.community.service.impl;

import com.xupt.community.dao.FollowDao;
import com.xupt.community.domain.Follow;
import com.xupt.community.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    FollowDao followDao;
    @Override
    public List<Follow> myFollow(Long memberId) {
        return followDao.myFollow(memberId);
    }

    @Override
    public List<Follow> followedMembers(Long communityId) {
        return followDao.followedMembers(communityId);
    }

    @Override
    public void delete(Follow follow) {
        followDao.delete(follow);
    }

    @Override
    public void add(Follow follow) {
        followDao.add(follow);
    }

    @Override
    public Follow getByMemberIdAndCommunityId(Long memberId, Long communityId) {
        Follow follow = new Follow();
        follow.setMemberId(memberId);
        follow.setCommunityId(communityId);
        return followDao.getByMemberIdAndCommunityId(follow);
    }
}
