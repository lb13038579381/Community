package com.xupt.community.dto;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommunityDto extends BaseDoamin {
    private static final long serialVersionUID = 1L;
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
}
