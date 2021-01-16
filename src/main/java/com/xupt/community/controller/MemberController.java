package com.xupt.community.controller;

import com.xupt.community.domain.Member;
import com.xupt.community.dto.MemberDto;
import com.xupt.community.exception.FrontException;
import com.xupt.community.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/16 10:19 下午
 */
@Controller
@Resource
public class MemberController {
    @Autowired
    MemberService memberService;

    /**
     * @description:新增用户
     * @params: [memberDto]
     * @return: void
     * @author: lb
     * @time: 2021/1/16 10:20 下午
     */
    public void addMember(MemberDto memberDto) {
        if (memberDto.getClazz() == null || memberDto.getClazz() < 0) {
            throw new FrontException("班级输入错误");
        }
        if (StringUtils.isBlank(memberDto.getNickName()) || memberDto.getNickName().length() > 15) {
            throw new FrontException("昵称过长或未输入");
        }
        if (StringUtils.isBlank(memberDto.getRealName())) {
            throw new FrontException("真实姓名未输入");
        }
        if (memberDto.getSex() == null) {
            throw new FrontException("性别未输入");
        }
        if (memberDto.getStudentNumber() == null) {
            throw new FrontException("学号未输入");
        }
        if (memberDto.getCollegeId() == null) {
            throw new FrontException("学院未输入");
        }
        if (memberDto.getMajorId() == null) {
            throw new FrontException("专业未输入");
        }
        if (memberDto.getGrade() == null) {
            throw new FrontException("年级未输入");
        }
        if (memberDto.getPhoneNumber() == null) {
            throw new FrontException("联系电话未输入");
        }
        if (memberDto.getClazz() == null) {
            throw new FrontException("班级未输入");
        }
        if (memberDto.getSignature() == null) {
            memberDto.setSignature("");
        }
        if (StringUtils.isBlank(memberDto.getFaceUrl())) {
            memberDto.setFaceUrl("");
        }
        memberService.addMember(memberDto);
    }
    /**
     * @description:获取社团成员
     * @params: [communityId]
     * @return: java.util.List<com.xupt.community.domain.Member>
     * @author: lb
     * @time: 2021/1/16 10:20 下午
     */
    public List<Member> getMembersByCommunityId(Long communityId) {

        return new ArrayList<>();
    }
}
