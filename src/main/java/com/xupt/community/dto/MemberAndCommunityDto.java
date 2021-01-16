package com.xupt.community.dto;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberAndCommunityDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long memberId;
    private Long communityId;
    //身份类型 1：普通成员 2：负责人（后续再加）
    private Integer type;
}
