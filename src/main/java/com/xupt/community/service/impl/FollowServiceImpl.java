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
    public List<Follow> getFansByMemberId(Long memberId) {
        return followDao.getFansByMemberId(memberId);
    }

    @Override
    public List<Follow> getFollersByMemberId(Long memberId) {
        return followDao.getFollowersByMemberId(memberId);
    }
}
