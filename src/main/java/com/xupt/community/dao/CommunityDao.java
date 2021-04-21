package com.xupt.community.dao;

import com.xupt.community.domain.Community;
import com.xupt.community.dto.CommunityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/16 8:38 下午
 */
@Mapper
public interface CommunityDao {
    /*
        //社团名称
    private String name;
    //社团类型 0：院级社团 1：校级社团 2：实验室 3：兴趣社团
    private Integer type;
    //所属学院id（校级社团和兴趣社团为-1）
    private Long ownerId;
    //负责人id
    private Long directorId;
    //社团简介
    private String introduction;
     */

    List<Community> getByName(String name);
    List<Community> getByType(List<Integer> types);
    List<Community> getByOwnerId(Long ownerId);
    void addCommunity(CommunityDto communityDto);
    List<Community> getByCommunityIds(List<Long> communityIds);
    List<Community> list();
    List<Community> getByIds(List<Long> communityIds);

    Community getById(Long communityId);
}
