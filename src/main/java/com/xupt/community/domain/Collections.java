package com.xupt.community.domain;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

import java.io.Serializable;

@Data
public class Collections extends BaseDoamin implements Serializable {
    private Long memberId;
    private Long informationId;
}
