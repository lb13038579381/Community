package com.xupt.community.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 社团申请vo
 * @author: lb
 * @time: 2021/3/28 11:44 下午
 */
@Data
public class CommunityApplyVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String apply;
    private Integer status;
    private String communityName;
}
