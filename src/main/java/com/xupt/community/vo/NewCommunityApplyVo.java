package com.xupt.community.vo;

import com.xupt.community.domain.NewCommunityApply;
import com.xupt.community.util.BaseDoamin;
import com.xupt.community.util.DateUtils;
import lombok.Data;

@Data
public class NewCommunityApplyVo extends BaseDoamin {
    private String name;
    private String introduction;
    private Long directorId;
    private String remark;
    //0 1 2
    private Integer status;
    private Long time;
    private String timeStr;

    public static NewCommunityApplyVo convert(NewCommunityApply apply) {
        NewCommunityApplyVo vo = new NewCommunityApplyVo();
        vo.setDirectorId(apply.getDirectorId());
        vo.setIntroduction(apply.getIntroduction());
        vo.setName(apply.getName());
        vo.setRemark(apply.getRemark());
        vo.setId(apply.id);
        vo.setTime(apply.getTime());
        vo.setStatus(apply.getStatus());
        vo.setTimeStr(DateUtils.convertToTimeStr(apply.getTime()));
        return vo;
    }
}
