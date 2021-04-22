package com.xupt.community.vo;

import lombok.Data;

@Data
public class InformationApplyVo {
    private Integer errorCode;
    private String title;
    private Integer status;
    private String applyTimeStr;
    private String endTimeStr;
    private Long informationId;
}
