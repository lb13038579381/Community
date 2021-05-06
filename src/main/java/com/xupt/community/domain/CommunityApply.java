package com.xupt.community.domain;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 社团申请
 * @author: lb
 * @time: 2021/1/16 11:03 下午
 */
@Data
public class CommunityApply extends BaseDoamin implements Serializable {
    private Long memberId;
    private Long communityId;
    private String apply;
    //申请状态 未处理：0 成功：1 拒绝：2
    private Integer status;
    private String communityName;
    private Long applyTime;
}
