package com.xupt.community.domain;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

import java.io.Serializable;

@Data
public class InformationApply extends BaseDoamin implements Serializable {
    private Long memberId;
    private Long informationId;
    private String phoneNumber;
    private String remark;
    private Integer status;
    private Long applyTime;
}
