package com.xupt.community.controller;

import com.xupt.community.constant.InformationApplyConstant;
import com.xupt.community.constant.InformationConstant;
import com.xupt.community.constant.MemberAndCommunityConstant;
import com.xupt.community.domain.*;
import com.xupt.community.service.*;
import com.xupt.community.util.DateUtils;
import com.xupt.community.util.PropertyExtractUtils;
import com.xupt.community.vo.CommunityDetailVo;
import com.xupt.community.vo.IVo;
import com.xupt.community.vo.InformationApplyVo;
import com.xupt.community.vo.InformationVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lb
 * @time: 2021/3/28 1:21 上午
 */
@ResponseBody
@RequestMapping("informationController")
@Controller
@Slf4j
public class InformationController {
    private static Logger logger = LoggerFactory.getLogger(InformationController.class);
    @Autowired
    InformationService informationService;

    @Autowired
    CommunityService communityService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    InformationApplyService informationApplyService;

    @Autowired
    FollowService followService;

    @Autowired
    MemberAndCommunityService memberAndCommunityService;

    /**
     * @description:最新动态
     * @params: []
     * @return: java.util.List<com.xupt.community.domain.Information>
     * @author: lb
     * @time: 2021/4/8 12:18 上午
     */
    @RequestMapping(value = "getLatestInformations", method = RequestMethod.GET)
    public List<InformationVo> getLatestInformations() {
        List<Information> informationList = informationService.getLatestInformations();
        List<InformationVo> result = new ArrayList<>();
        for (Information information : informationList) {
            InformationVo informationDto = InformationVo.convert(information);
            Long communityId = information.getCommunityId();
            Community community = communityService.getById(communityId);
            informationDto.setCommunityName(community.getName());
            result.add(informationDto);
        }
        if (CollectionUtils.isNotEmpty(result)) {
            return result.subList(0, result.size() / 2);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @description:发布动态
     * @params: [information]
     * @return: java.lang.String
     * @author: lb
     * @time: 2021/4/8 11:32 下午
     */
    @RequestMapping("addInformation")
    public String addInformation(Information information) {
        try {
            information.setGmtCreate(System.currentTimeMillis());
            informationService.addInformation(information);
        } catch (Exception e) {
            return "false";
        }
        return "success";
    }

    /**
     * @description:更多咨询
     * @params: []
     * @return: java.util.List<com.xupt.community.domain.Information>
     * @author: lb
     * @time: 2021/4/8 11:36 下午
     */
    @RequestMapping("list")
    public List<Information> list() {
        List<Information> result = informationService.list();
        if (CollectionUtils.isNotEmpty(result)) {
            return result.subList(0, result.size() / 2);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @description:活动详情页
     * @params: [id, memberId]
     * @return: com.xupt.community.vo.InformationVo
     * @author: lb
     * @time: 2021/4/23 12:55 上午
     */
    @RequestMapping("getInformationById")
    public InformationVo getInformationById(Long id, Long memberId) {
        if (id == null) {
            return new InformationVo();
        }
        Information information = informationService.getByIds(Arrays.asList(id)).get(0);
        Long communityId = information.getCommunityId();
        Community community = communityService.getById(communityId);
        InformationVo result = InformationVo.convert(information);
        result.setCommunityName(community.getName());
        //活动状态
        Long startTime = information.getStartTime();
        Long endTime = information.getEndTime();
        Long nowTime = System.currentTimeMillis();
        if (nowTime < startTime) {
            result.setStatus(InformationConstant.NOT_START);
        } else if (nowTime < endTime) {
            result.setStatus(InformationConstant.START);
            InformationApply informationApply = informationApplyService.getByMemberIdAndInformationId(memberId, id);
            if (informationApply != null) {
                result.setApply(true);
            }
        } else {
            result.setStatus(InformationConstant.END);
        }
        //收藏状态
        Collections collections = collectionService.getByMemberIdAndInformationId(memberId, id);
        if (collections != null) {
            result.setCollect(1L);
        }
        return result;

    }

    /**
     * @description:收藏帖子
     * @params: [memberId, informationId]
     * @return: java.lang.String
     * @author: lb
     * @time: 2021/4/23 12:55 上午
     */
    @RequestMapping("collect")
    public String collectInformation(Long memberId, Long informationId) {
        Collections collections = new Collections();
        collections.setMemberId(memberId);
        collections.setInformationId(informationId);
        Collections result = collectionService.getByMemberIdAndInformationId(memberId, informationId);
        if (result != null) {
            collectionService.delete(collections);
        } else {
            collectionService.add(collections);
        }
        return "success";
    }

    /**
     * @description:增加活动报名申请
     * @params: [apply]
     * @return: com.xupt.community.vo.InformationApplyVo
     * @author: lb
     * @time: 2021/4/23 12:55 上午
     */
    @RequestMapping(value = "addApply", method = RequestMethod.POST)
    public InformationApplyVo addApply(@RequestBody InformationApply apply) {
        InformationApplyVo result = new InformationApplyVo();
        try {
            List<Information> informationList = informationService.getByIds(Arrays.asList(apply.getInformationId()));
            Information information = informationList.get(0);
            if (information.getCount().equals(information.getPeople())) {
                result.setErrorCode(InformationApplyConstant.FULLED);
                return result;
            }
            apply.setApplyTime(System.currentTimeMillis());
            apply.setStatus(InformationApplyConstant.PENDING);
            apply.setApplyTime(System.currentTimeMillis());
            informationApplyService.add(apply);
            informationService.addCount(apply.getInformationId());
        } catch (Exception e) {
            result.setErrorCode(InformationApplyConstant.FAIL);
            return result;
        }
        result.setErrorCode(InformationApplyConstant.SUCCESS);
        return result;
    }

    /**
     * @description:我的收藏
     * @params: [memberId]
     * @return: java.util.List<com.xupt.community.domain.Information>
     * @author: lb
     * @time: 2021/4/23 12:55 上午
     */
    @RequestMapping("myCollect")
    public List<InformationVo> myCollect(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        List<Collections> collectionsList = collectionService.getCollectionsByMemberId(memberId);
        List<Long> informationIds = PropertyExtractUtils.getByPropertyValue(collectionsList, "informationId", Long.class);
        List<Information> informationList = informationService.getByIds(informationIds);
        List<InformationVo> result = new ArrayList<>();
        for (Information information : informationList) {
            InformationVo vo = InformationVo.convert(information);
            result.add(vo);
        }
        if (CollectionUtils.isNotEmpty(informationList)) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @description:我的报名
     * @params: [memberId]
     * @return: java.util.List<com.xupt.community.vo.InformationApplyVo>
     * @author: lb
     * @time: 2021/4/23 1:58 上午
     */
    @RequestMapping("myApply")
    public List<InformationApplyVo> myApply(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        List<InformationApply> informationApplies = informationApplyService.getByMemberId(memberId);
        Map<Long, List<InformationApply>> informationIdAndApply = PropertyExtractUtils.getListMapFromListByProperty(informationApplies, "informationId", Long.class);
        List<Long> informationIds = PropertyExtractUtils.getByPropertyValue(informationApplies, "informationId", Long.class);
        List<Information> informationList = informationService.getByIds(informationIds);
        List<InformationApplyVo> result = new ArrayList<>();
        for (Information information : informationList) {
            InformationApply apply = informationIdAndApply.get(information.getId()).get(0);
            InformationApplyVo vo = new InformationApplyVo();
            vo.setTitle(information.getTitle());
            vo.setStatus(apply.getStatus());
            vo.setApplyTimeStr(DateUtils.convertToTimeStr(apply.getApplyTime()));
            vo.setEndTimeStr(DateUtils.convertToTimeStr(information.getEndTime()));
            vo.setInformationId(information.getId());
            result.add(vo);
        }
        if (CollectionUtils.isNotEmpty(result)) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping("communityInformations")
    public IVo communityInformations(Long communityId,Long memberId) {
        if (communityId == null) {
            return new IVo();
        }
        Boolean isDirector = false;
        if (memberId != null) {
            List<MemberAndCommunity> memberAndCommunityList = memberAndCommunityService.getByMemberId(memberId);
            for (MemberAndCommunity memberAndCommunity : memberAndCommunityList) {
                if (memberAndCommunity.getType().equals(MemberAndCommunityConstant.MANAGER)) {
                    isDirector = true;
                }
            }
        }
        List<Information> informationList = informationService.getByCommunityId(communityId);
        IVo res = new IVo();
        if (CollectionUtils.isNotEmpty(informationList)) {
            List<InformationVo> result = new ArrayList<>();
            for (Information information : informationList) {
                InformationVo vo = InformationVo.convert(information);
                Community community = communityService.getById(information.getCommunityId());
                vo.setCommunityName(community.getName());
                Long startTime = information.getStartTime();
                Long endTime = information.getEndTime();
                Long nowTime = System.currentTimeMillis();
                if (nowTime < startTime) {
                    vo.setStatus(InformationConstant.NOT_START);
                } else if (nowTime < endTime) {
                    vo.setStatus(InformationConstant.START);
                } else {
                    vo.setStatus(InformationConstant.END);
                }
                result.add(vo);
            }
            res.setInformationVoList(result);
            res.setIsDirector(isDirector);
            return res;
        } else {
            return res;
        }
    }

    /**
     * @description:所有活动
     * @params: []
     * @return: java.util.List<com.xupt.community.vo.InformationVo>
     * @author: lb
     * @time: 2021/4/24 1:33 上午
     */
    @RequestMapping("all")
    public IVo all(Long memberId) {
        List<Information> informationList = informationService.list();
        List<InformationVo> result = new ArrayList<>();
        Boolean isDirector = false;
        if (memberId != null) {
            List<MemberAndCommunity> memberAndCommunityList = memberAndCommunityService.getByMemberId(memberId);
            for (MemberAndCommunity memberAndCommunity : memberAndCommunityList) {
                if (memberAndCommunity.getType().equals(MemberAndCommunityConstant.MANAGER)) {
                    isDirector = true;
                }
            }
        }
        if (CollectionUtils.isNotEmpty(informationList)) {
            for (Information information : informationList) {
                InformationVo vo = InformationVo.convert(information);
                Community community = communityService.getById(vo.getCommunityId());
                vo.setCommunityName(community.getName());
                vo.setType(1);
                result.add(vo);
            }
            IVo res = new IVo();
            res.setInformationVoList(result);
            res.setIsDirector(isDirector);
            return res;
        } else {
            IVo res = new IVo();
            res.setInformationVoList(new ArrayList<>());
            return res;
        }
    }

    @RequestMapping("myFollow")
    public List<InformationVo> myFollow(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        //获取关注的社团id
        List<Follow> follows = followService.myFollow(memberId);
        List<Long> communityIds = PropertyExtractUtils.getByPropertyValue(follows, "communityId", Long.class);
        if (CollectionUtils.isEmpty(communityIds)) {
            return new ArrayList<>();
        }
        List<Information> informationList = informationService.getByCommunityIds(communityIds);
        List<InformationVo> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(informationList)) {
            for (Information information : informationList) {
                InformationVo vo = InformationVo.convert(information);
                Community community = communityService.getById(vo.getCommunityId());
                vo.setCommunityName(community.getName());
                vo.setType(1);
                result.add(vo);
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping("collectList")
    public List<InformationVo> collectList(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        List<Collections> collectionsList = collectionService.getCollectionsByMemberId(memberId);
        if (CollectionUtils.isNotEmpty(collectionsList)) {
            List<Long> informationIdList = PropertyExtractUtils.getByPropertyValue(collectionsList, "informationId", Long.class);
            List<Information> informationList = informationService.getByIds(informationIdList);
            List<InformationVo> result = new ArrayList<>();
            for (Information information : informationList) {
                InformationVo vo = InformationVo.convert(information);
                Community community = communityService.getById(vo.getCommunityId());
                vo.setCommunityName(community.getName());
                vo.setType(1);
                result.add(vo);
            }
            return result;
        } else {
            return new ArrayList<>();
        }

    }

    @RequestMapping("newActivity")
    public InformationVo newActivity(String title,String text,String address,String startTime,String endTime,String time,Integer people,Long communityId,Long memberId) {
        InformationVo result = new InformationVo();
            Information information = new Information();
            information.setTitle(title);
            information.setText(text);
            information.setAddress(address);
            information.setStartTime(DateUtils.convertToLong(startTime));
            information.setEndTime(DateUtils.convertToLong(endTime));
            information.setTime(DateUtils.convertToLong(time));
            information.setPeople(people);
            information.setCount(0);
            information.setCommunityId(communityId);
            information.setPosterId(memberId);
            information.setThumbnail("333.jpg");
            information.setGmtCreate(System.currentTimeMillis());
            informationService.addInformation(information);
            result.setErrorCode(0);
            return result;

    }

}
