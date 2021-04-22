package com.xupt.community.controller;

import com.xupt.community.constant.MemberAndCommunityConstant;
import com.xupt.community.domain.Community;
import com.xupt.community.domain.Follow;
import com.xupt.community.domain.Member;
import com.xupt.community.exception.FrontException;
import com.xupt.community.service.CommunityService;
import com.xupt.community.service.FollowService;
import com.xupt.community.service.MemberAndCommunityService;
import com.xupt.community.service.MemberService;
import com.xupt.community.vo.CommunityApplyVo;
import com.xupt.community.vo.CommunityDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lb
 * @time: 2021/3/28 1:09 上午
 */
@RestController
@Slf4j
@RequestMapping("communityController")
public class CommunityController {
    private static Logger logger = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    CommunityService communityService;

    @Autowired
    MemberAndCommunityService memberAndCommunityService;

    @Autowired
    MemberService memberService;

    @Autowired
    FollowService followService;
    @RequestMapping(value = "getCommunityByName")
    public List<Community> getCommunityByName(String words) {
        System.out.println(words);
        if (words == null || words.length() == 0) {
            throw new FrontException("搜索名称不能为空");
        }
        List<Community> communityList = communityService.getByName(words);
        if (CollectionUtils.isNotEmpty(communityList)) {
            return communityList;
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping("getCommunityListByStudentNumber")
    public List<Community> getCommunityListByStudentNumber(Long memberId) {
        if (memberId == null || memberId.toString().length() == 0) {
            throw new FrontException("用户id不能为空");
        }
        List<Long> communityIds = memberAndCommunityService.getCommunityIdsByMemberId(memberId);
        if (CollectionUtils.isEmpty(communityIds)) {
            return new ArrayList<>();
        }
        List<Community> communityList = communityService.getByIds(communityIds);
        if (CollectionUtils.isEmpty(communityList)) {
            return new ArrayList<>();
        }
        return communityList;
    }

    /**
     * @description:我的社团 展示我管理的社团和参加的社团
     * @params:
     * @return:
     * @author: lb
     * @time: 2021/3/28 11:50 下午
     */
    @RequestMapping("communityJoined")
    public List<Community> communityJoined(Long memberId) {
        if (memberId == null || memberId.toString().length() != 8) {
            throw new FrontException("用户id不符合规范");
        }
        List<Long> communityIdList = memberAndCommunityService.getCommunityIdsByMemberIdAndType(memberId, MemberAndCommunityConstant.MEMBER);
        List<Community> communityList = communityService.getByIds(communityIdList);
        if (CollectionUtils.isNotEmpty(communityList)) {
            return communityList;
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping("communityManaged")
    public List<Community> communityManaged(Long memberId) {
        if (memberId == null || memberId.toString().length() != 8) {
            throw new FrontException("用户id不符合规范");
        }
        List<Long> communityIdList = memberAndCommunityService.getCommunityIdsByMemberIdAndType(memberId, MemberAndCommunityConstant.MANAGER);
        List<Community> communityList = communityService.getByIds(communityIdList);
        if (CollectionUtils.isNotEmpty(communityList)) {
            return communityList;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @description:我的社团页面点击，进入社团展示页面,分页获取社团成员
     * @params:
     * @return:
     * @author: lb
     * @time: 2021/3/29 12:08 上午
     */
//    public List<Member> getPageMemberListByCommunityId(Long communityId,)

    /**
     * @description:全部社团
     * @params: []
     * @return: java.util.List<com.xupt.community.domain.Community>
     * @author: lb
     * @time: 2021/4/8 10:18 下午
     */
    @RequestMapping("list")
    public List<Community> list() {
        List<Community> result = communityService.list();
        if (CollectionUtils.isNotEmpty(result)) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping("getById")
    public CommunityDetailVo getById(Long communityId,Long memberId) {
        if (communityId == null) {
            return new CommunityDetailVo();
        }
        Community community = communityService.getById(communityId);
        if (community != null) {
            CommunityDetailVo result = CommunityDetailVo.convert2Vo(community);
            List<Long> memberIds = memberAndCommunityService.getMemberIdsByCommunityId(communityId);
            List<Member> members = memberService.getByIds(memberIds);
            result.setMembers(members);
            Follow follow = followService.getByMemberIdAndCommunityId(memberId,communityId);
            if(follow != null) {
                result.setFollow(true);
            }
            return result;
        } else {
            return new CommunityDetailVo();
        }
    }

    @RequestMapping("myCommunity")
    public List<Community> myCommunity(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        List<Long> communityIds = memberAndCommunityService.getCommunityIdsByMemberId(memberId);
        if (CollectionUtils.isEmpty(communityIds)) {
            return new ArrayList<>();
        }
        List<Community> myCommunity = communityService.getByIds(communityIds);
        if (CollectionUtils.isNotEmpty(myCommunity)) {
            return myCommunity;
        } else {
            return new ArrayList<>();
        }
    }
}
