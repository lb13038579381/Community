package com.xupt.community.domain;

import lombok.Data;

@Data
public class Information {
    private String title;
    private String text;
    private Long time;
    private String address;
    private String covPicUrl;
    private Long posterId;
    private Long communityId;
    private Long gmtCreate;
}
