package com.xupt.community.service.impl;

import com.xupt.community.dao.MemberAndCommunityDao;
import com.xupt.community.dto.MemberAndCommunityDto;
import com.xupt.community.service.MemberAndCommunityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/16 10:54 下午
 */
@Service
public class MemberAndCommunityServiceImpl implements MemberAndCommunityService {

    @Autowired
    MemberAndCommunityDao memberAndCommunityDao;

    private static final Integer CANT_FIND_ERROR = -1;

    @Override
    public List<Long> getMemberIdsByCommunityId(Long communityId) {
        List<Long> res = memberAndCommunityDao.getMemberIdsByCommunityId(communityId);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public Integer getTypeByCommunityIdAndMemberId(Long memberId, Long communityId) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("communityId", communityId);
        Integer res = memberAndCommunityDao.getTypeByCommunityIdAndMemberId(params);
        return res == null ? CANT_FIND_ERROR : res;
    }

    @Override
    public List<Long> getCommunityIdsByMemberId(Long memberId) {
        List<Long> res = memberAndCommunityDao.getCommunityIdsByMemberId(memberId);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public void add(MemberAndCommunityDto dto) {
        memberAndCommunityDao.add(dto);
    }

    @Override
    public void delete(MemberAndCommunityDto dto) {
        memberAndCommunityDao.delete(dto);
    }

    @Override
    public List<Long> getCommunityIdsByMemberIdAndType(Long memberId, Integer type) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("type", type);
        return memberAndCommunityDao.getCommunityIdsByMemberIdAndType(params);
    }
}
