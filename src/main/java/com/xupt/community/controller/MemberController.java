package com.xupt.community.controller;

import com.xupt.community.domain.Follow;
import com.xupt.community.domain.Member;
import com.xupt.community.dto.MemberDto;
import com.xupt.community.exception.FrontException;
import com.xupt.community.service.FollowService;
import com.xupt.community.service.MemberService;
import com.xupt.community.util.MD5Utils;
import com.xupt.community.util.PropertyExtractUtils;
import com.xupt.community.vo.LoginVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/16 10:19 下午
 */
@RestController
@RequestMapping("memberController")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    FollowService followService;

    /**
     * @description:新增用户
     * @params: [memberDto]
     * @return: void
     * @author: lb
     * @time: 2021/1/16 10:20 下午
     */
    @RequestMapping("login")
    public LoginVo login(String username, String password) {
        System.out.println(username + password);
        LoginVo loginVo = new LoginVo();
        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            loginVo.setMsg("用户名和密码不能为空");
            loginVo.setStatusCode("101");
            return loginVo;
        }
        if (username.length() != 8) {
            loginVo.setMsg("学号长度不符合规范");
            loginVo.setStatusCode("101");
            return loginVo;
        }
        Member member = memberService.getByStudentNumber(username);
        System.out.println(member);
        if (member == null) {
            loginVo.setMsg("用户名不存在");
            loginVo.setStatusCode("101");
            return loginVo;
        }
        if (!MD5Utils.toMD5(password).equals(member.getPassword())) {
            loginVo.setStatusCode("101");
            loginVo.setMsg("密码错误");
            return loginVo;
        } else {
            loginVo.setStatusCode("200");
            loginVo.setToken("token");
            return loginVo;
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

    @RequestMapping("registry")
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

    /**
     * @description:我的关注
     * @params:
     * @return:
     * @author: lb
     * @time: 2021/4/7 11:53 下午
     */
    @RequestMapping("myFollowers")
    public List<Member> getMyFollowers(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        List<Follow> followList = followService.getFollersByMemberId(memberId);
        List<Long> followerIdList = PropertyExtractUtils.getByPropertyValue(followList, "followerId", Long.class);
        List<Member> result = new ArrayList<>();
        for (Long followerId : followerIdList) {
            Member member = memberService.getById(followerId);
            if (member != null) {
                result.add(member);
            }
        }
        if (CollectionUtils.isNotEmpty(result)) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @description:我的粉丝列表
     * @params:
     * @return:
     * @author: lb
     * @time: 2021/4/7 11:57 下午
     */
    @RequestMapping("myFans")
    public List<Member> getMyFans(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        List<Follow> followList = followService.getFansByMemberId(memberId);
        List<Long> fanIdList = PropertyExtractUtils.getByPropertyValue(followList, "memberId", Long.class);
        List<Member> result = new ArrayList<>();
        for (Long fanId : fanIdList) {
            Member member = memberService.getById(fanId);
            if (member != null) {
                result.add(member);
            }
        }
        if (CollectionUtils.isNotEmpty(result)) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
