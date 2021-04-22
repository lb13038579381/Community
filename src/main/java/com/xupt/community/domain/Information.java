package com.xupt.community.domain;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

@Data
public class Information extends BaseDoamin {
    private String title;
    private String text;
    private Long time;
    private String address;
    private String covPicUrl;
    private Long posterId;
    private Long communityId;
    private Long gmtCreate;
    private Long startTime;
    private Long endTime;
}
