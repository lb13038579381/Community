package com.xupt.community.controller;

import com.xupt.community.domain.Member;
import com.xupt.community.dto.MemberDto;
import com.xupt.community.exception.FrontException;
import com.xupt.community.service.MemberService;
import com.xupt.community.util.MD5Utils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("memberController")
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
    @RequestMapping("login")
    public Member login(String username, String password) {
        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            throw new FrontException("用户名和密码不能为空");
        }
        Long studentNumber;
        try {
            studentNumber = Long.parseLong(username);
        } catch (Exception e) {
            throw new FrontException("用户名不合规范");
        }
        if (studentNumber.toString().length() != 8) {
            throw new FrontException("学号长度不符合规范");
        }
        Member member = memberService.getByStudentNumber(studentNumber);
        if (member == null) {
            throw new FrontException("学号不存在");
        }
        if (!MD5Utils.toMD5(password).equals(member.getPassword())) {
            throw new FrontException("密码错误");
        } else {
            return member;
        }
    }

    @RequestMapping("getByNickname")
    public List<Member> getByNickname(String nickname) {
        if (nickname == null || nickname.length() == 0) {
            throw new FrontException("搜索名不能为空");
        }
        List<Member> memberList = memberService.getByNickName(nickname);
        if (CollectionUtils.isNotEmpty(memberList)) {
            return memberList;
        } else {
            return new ArrayList<>();
        }
    }

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
