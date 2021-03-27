package com.xupt.community.controller;

import com.xupt.community.domain.Community;
import com.xupt.community.exception.FrontException;
import com.xupt.community.service.CommunityService;
import com.xupt.community.service.MemberAndCommunityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lb
 * @time: 2021/3/28 1:09 上午
 */
@Controller
@Slf4j
@Resource
@RequestMapping("communityController")
public class CommunityController {
    private static Logger logger = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    CommunityService communityService;

    @Autowired
    MemberAndCommunityService memberAndCommunityService;

    @RequestMapping("getCommunityByName")
    public List<Community> getCommunityByName(String name) {
        if (name == null || name.length() == 0) {
            throw new FrontException("搜索名称不能为空");
        }
        List<Community> communityList = communityService.getByName(name);
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
}
