package com.xupt.community.vo;

import com.xupt.community.domain.Community;
import com.xupt.community.domain.CommunityApply;
import com.xupt.community.util.BaseDoamin;
import com.xupt.community.util.DateUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 社团申请vo
 * @author: lb
 * @time: 2021/3/28 11:44 下午
 */
@Data
public class CommunityApplyVo extends BaseDoamin {
    private static final long serialVersionUID = 1L;
    private String apply;
    private Integer status;
    private String communityName;
    private Integer errorCode;
    private String applyTimeStr;
    private Long memberId;
    private Long communityId;
    private String username;
    public static CommunityApplyVo convert(CommunityApply apply) {
        CommunityApplyVo result = new CommunityApplyVo();
        result.setStatus(apply.getStatus());
        result.setApply(apply.getApply());
        result.setCommunityId(apply.getCommunityId());
        result.setMemberId(apply.getMemberId());
        result.setApplyTimeStr(DateUtils.convertToTimeStr(apply.getApplyTime()));
        return result;
    }

}
