package com.xupt.community.domain;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

import java.io.Serializable;

@Data
public class Follow extends BaseDoamin implements Serializable {
    private Long memberId;
    private Long communityId;
}
