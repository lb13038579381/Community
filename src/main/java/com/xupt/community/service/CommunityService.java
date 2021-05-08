package com.xupt.community.service;

import com.xupt.community.domain.Community;
import com.xupt.community.dto.CommunityDto;

import java.util.List;

/**
 * @description: 
 * @author: lb
 * @time: 2021/1/14 8:40 下午
 */  
public interface CommunityService {
    List<Community> getByName(String name);
    List<Community> getByType(List<Integer> types);
    List<Community> getByOwnerId(Long ownerId);
    Long addCommunity(CommunityDto communityDto);
    List<Community> getByIds(List<Long> communityIds);
    List<Community> list();

    Community getById(Long communityId);
}
