package com.xupt.community.vo;

import com.xupt.community.domain.Community;
import com.xupt.community.domain.Member;
import lombok.Data;

import java.util.List;

@Data
public class CommunityDetailVo {
    private Long id;
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

    private List<Member> members;

    private Boolean follow;

    public static CommunityDetailVo convert2Vo(Community community) {

        CommunityDetailVo result = new CommunityDetailVo();
        result.setName(community.getName());
        result.setDirectorId(community.getDirectorId());
        result.setId(community.getId());
        result.setIntroduction(community.getIntroduction());
        result.setOwnerId(community.getOwnerId());
        result.setType(community.getType());
        return result;

    }
}
