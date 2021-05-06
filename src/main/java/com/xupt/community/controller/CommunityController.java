package com.xupt.community.controller;

import com.xupt.community.constant.CommunityApplyConstant;
import com.xupt.community.constant.MemberAndCommunityConstant;
import com.xupt.community.domain.*;
import com.xupt.community.dto.MemberAndCommunityDto;
import com.xupt.community.exception.FrontException;
import com.xupt.community.service.*;
import com.xupt.community.util.DateUtils;
import com.xupt.community.util.PropertyExtractUtils;
import com.xupt.community.vo.ApplyVo;
import com.xupt.community.vo.CommunityApplyVo;
import com.xupt.community.vo.CommunityDetailVo;
import com.xupt.community.vo.CommunityVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    CommunityApplyService communityApplyService;

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
    public CommunityDetailVo getById(Long communityId, Long memberId) {
        if (communityId == null) {
            return new CommunityDetailVo();
        }
        Community community = communityService.getById(communityId);
        if (community != null) {
            CommunityDetailVo result = CommunityDetailVo.convert2Vo(community);
            List<Long> memberIds = memberAndCommunityService.getMemberIdsByCommunityId(communityId);
            List<Member> members = memberService.getByIds(memberIds);
            result.setMembers(members);
            Follow follow = followService.getByMemberIdAndCommunityId(memberId, communityId);
            MemberAndCommunity memberAndCommunity = memberAndCommunityService.getByMemberIdAndCommunityId(memberId, communityId);
            if (memberAndCommunity != null) {
                result.setIsJoined(true);
            }
            if (follow != null) {
                result.setFollow(true);
            }
            return result;
        } else {
            return new CommunityDetailVo();
        }
    }

    @RequestMapping("myCommunity")
    public List<CommunityVo> myCommunity(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        List<Long> communityIds = memberAndCommunityService.getCommunityIdsByMemberId(memberId);
        if (CollectionUtils.isEmpty(communityIds)) {
            return new ArrayList<>();
        }
        List<Community> myCommunity = communityService.getByIds(communityIds);
        List<CommunityVo> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(myCommunity)) {
            for (Community community : myCommunity) {
                CommunityVo vo = CommunityVo.convert(community);
                if (memberAndCommunityService.getTypeByCommunityIdAndMemberId(memberId, community.getId()).equals(MemberAndCommunityConstant.MANAGER)) {
                    vo.setIsDirector(true);
                } else {
                    vo.setIsDirector(false);
                }
                result.add(vo);
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping("addApply")
    public CommunityApplyVo addApply(@RequestBody CommunityApply apply) {
        Long memberId = apply.getMemberId();
        Long communityId = apply.getCommunityId();
        CommunityApply communityApply = communityApplyService.getByMemberIdAndCommunityId(memberId, communityId);
        CommunityApplyVo result = new CommunityApplyVo();
        if (communityApply != null) {
            result.setErrorCode(CommunityApplyConstant.REPEAT);
            return result;
        }
        apply.setStatus(CommunityApplyConstant.NOT_HANDLE);
        Community community = communityService.getById(communityId);
        if (community == null) {
            result.setErrorCode(CommunityApplyConstant.ERROR);
            return result;
        }
        apply.setCommunityName(community.getName());
        apply.setApplyTime(System.currentTimeMillis());
        communityApplyService.add(apply);
        result.setErrorCode(CommunityApplyConstant.SUCCESS);
        return result;
    }

    @RequestMapping("myApply")
    public List<CommunityApplyVo> myApply(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        List<CommunityApply> applies = communityApplyService.getCommunityAppliesByMemberId(memberId);
        if (CollectionUtils.isEmpty(applies)) {
            return new ArrayList<>();
        }
        List<Long> communityIds = PropertyExtractUtils.getByPropertyValue(applies, "communityId", Long.class);
        List<Community> communityList = communityService.getByIds(communityIds);
        Map<Long, List<Community>> communityIdMap = PropertyExtractUtils.getListMapFromListByProperty(communityList, "id", Long.class);
        List<CommunityApplyVo> result = new ArrayList<>();
        for (CommunityApply apply : applies) {
            CommunityApplyVo vo = CommunityApplyVo.convert(apply);
            vo.setCommunityName(communityIdMap.get(vo.getCommunityId()).get(0).getName());
            result.add(vo);
        }
        return result;
    }

    @RequestMapping("adoptApply")
    public ApplyVo adoptApply(Long id) {
        CommunityApply apply = communityApplyService.getById(id);
        ApplyVo vo = new ApplyVo();
        if (apply.getStatus().equals(CommunityApplyConstant.NOT_HANDLE)) {
            communityApplyService.adoptApply(id);
            MemberAndCommunityDto dto = new MemberAndCommunityDto();
            dto.setMemberId(apply.getMemberId());
            dto.setCommunityId(apply.getCommunityId());
            dto.setType(MemberAndCommunityConstant.MEMBER);
            memberAndCommunityService.add(dto);
            vo.setMsg("通过成功");
            return vo;
        } else if (apply.getStatus().equals(CommunityApplyConstant.SUCCESS)) {
            vo.setMsg("无须重复通过");
            return vo;
        } else if (apply.getStatus().equals(CommunityApplyConstant.APPLY_FAIL)) {
            vo.setMsg("已拒绝，无法重新通过");
            return vo;
        }
        vo.setMsg("未知异常");
        return vo;
    }

    @RequestMapping("refuseApply")
    public ApplyVo refuseApply(Long id) {
        CommunityApply apply = communityApplyService.getById(id);
        ApplyVo vo = new ApplyVo();
        if (apply.getStatus().equals(CommunityApplyConstant.NOT_HANDLE)) {
            communityApplyService.refuseApply(id);
            vo.setMsg("拒绝成功");
            return vo;
        } else if (apply.getStatus().equals(CommunityApplyConstant.SUCCESS)) {
            vo.setMsg("已通过，无法拒绝");
            return vo;
        } else if (apply.getStatus().equals(CommunityApplyConstant.APPLY_FAIL)) {
            vo.setMsg("无须重复拒绝");
            return vo;
        }
        vo.setMsg("未知异常");
        return vo;
    }

    @RequestMapping("deleteApply")
    public ApplyVo deleteApply(Long id) {
        ApplyVo vo = new ApplyVo();
        try {
            communityApplyService.deleteById(id);
        } catch (Exception e) {
            vo.setMsg("未知异常");
            return vo;
        }
        vo.setMsg("删除成功");
        return vo;
    }
    @RequestMapping(value = "applyList",method = RequestMethod.POST)
    public List<CommunityApplyVo> applyList(Long communityId) {
        List<CommunityApply> applyList = communityApplyService.getCommunityAppliesByCommunityId(communityId);
        List<CommunityApplyVo> result = new ArrayList<>();
        for (CommunityApply apply : applyList) {
            CommunityApplyVo vo = new CommunityApplyVo();
            vo.setApply(apply.getApply());
            vo.setId(apply.getId());
            Member member = memberService.getById(apply.getMemberId());
            vo.setUsername(member.getRealName());
            vo.setApplyTimeStr(DateUtils.convertToTimeStr(apply.getApplyTime()));
            vo.setStatus(apply.getStatus());
            result.add(vo);
        }
        return result;
    }
}