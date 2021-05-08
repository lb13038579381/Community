package com.xupt.community.service.impl;

import com.xupt.community.dao.CommunityDao;
import com.xupt.community.domain.Community;
import com.xupt.community.dto.CommunityDto;
import com.xupt.community.exception.FrontException;
import com.xupt.community.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/14 6:14 下午
 */
@Service
@Slf4j
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityDao communityDao;

    @Override
    public List<Community> getByName(String name) {
        List<Community> res = communityDao.getByName(name);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public List<Community> getByType(List<Integer> types) {
        List<Community> res = communityDao.getByType(types);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public List<Community> getByOwnerId(Long ownerId) {
        List<Community> res = communityDao.getByOwnerId(ownerId);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public Long addCommunity(CommunityDto communityDto) {
        return communityDao.addCommunity(communityDto);
    }

    @Override
    public List<Community> getByIds(List<Long> communityIds) {
        return communityDao.getByIds(communityIds);
    }

    @Override
    public List<Community> list() {
        return communityDao.list();
    }

    @Override
    public Community getById(Long communityId) {
        return communityDao.getById(communityId);
    }
}
