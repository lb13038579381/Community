package com.xupt.community.domain;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户和社团关联类
 * @author: lb
 * @time: 2021/1/16 10:32 下午
 */
@Data
public class MemberAndCommunity extends BaseDoamin implements Serializable {
    private Long memberId;
    private Long communityId;
    //身份类型 1：普通成员 2：负责人（后续再加）
    private Integer type;
}
