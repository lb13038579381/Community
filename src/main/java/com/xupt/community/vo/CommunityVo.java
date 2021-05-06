package com.xupt.community.vo;

import com.xupt.community.domain.Community;
import com.xupt.community.util.BaseDoamin;
import lombok.Data;

@Data
public class CommunityVo extends BaseDoamin {
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

    private String thumbnail;
    private Boolean isDirector;

    public static CommunityVo convert(Community community) {
        CommunityVo vo = new CommunityVo();
        vo.setId(community.getId());
        vo.setDirectorId(community.getDirectorId());
        vo.setIntroduction(community.getIntroduction());
        vo.setName(community.getName());
        vo.setOwnerId(community.getOwnerId());
        vo.setThumbnail(community.getThumbnail());
        vo.setType(community.getType());
        return vo;
    }
}
