package com.xupt.community.domain;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

@Data
public class NewCommunityApply extends BaseDoamin {
    private String name;
    private String introduction;
    private Long directorId;
    private String remark;
    //0 1 2
    private Integer status;
    private Long time;
    private String thumbnail;
}
