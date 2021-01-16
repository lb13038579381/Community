package com.xupt.community.domain;

import com.xupt.community.util.BaseDoamin;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户domain类
 * @author: lb
 * @time: 2021/1/14 4:35 下午
 */
@Data
public class Member extends BaseDoamin implements Serializable {
    private static final long serialVersionUID = 1L;
    //昵称
    private String nickName;
    //真实姓名
    private String realName;
    //学号
    private Long studentNumber;
    //性别 0：男 1：女
    private Integer sex;
    //头像url
    private String faceUrl;
    //学院id
    private Long collegeId;
    //专业
    private String major;
    //年级
    private Integer grade;
    //个性签名
    private String signature;
    //联系电话
    private String phoneNumber;
    //班级
    private Integer clazz;

}
