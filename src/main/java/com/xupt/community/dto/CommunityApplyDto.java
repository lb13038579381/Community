package com.xupt.community.dto;

import lombok.Data;

import java.io.Serializable;
/**
 * @description:
 * @author: lb
 * @time: 2021/1/16 11:05 下午
 */
@Data
public class CommunityApplyDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long memberId;
    private Long communityId;
    private String apply;
    private Integer status;
}
