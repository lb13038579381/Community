package com.xupt.community.controller;

import com.xupt.community.domain.Collections;
import com.xupt.community.domain.Information;
import com.xupt.community.domain.Member;
import com.xupt.community.service.CollectionService;
import com.xupt.community.service.InformationService;
import com.xupt.community.service.MemberService;
import com.xupt.community.util.PropertyExtractUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("collectionController")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    InformationService informationService;

    @Autowired
    MemberService memberService;

    /**
     * @description:我的收藏
     * @params: [memberId]
     * @return: java.util.List<com.xupt.community.domain.Information>
     * @author: lb
     * @time: 2021/4/8 12:14 上午
     */
    @RequestMapping("myCollections")
    public List<Information> getCollectionsByMemberId(Long memberId) {
        if (memberId == null) {
            return new ArrayList<>();
        }
        List<Collections> collectionList = collectionService.getCollectionsByMemberId(memberId);
        List<Long> informationIdList = PropertyExtractUtils.getByPropertyValue(collectionList, "informationId", Long.class);
        List<Information> result = informationService.getByIds(informationIdList);
        if (CollectionUtils.isNotEmpty(result)) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @description:收藏的用户
     * @params: [informationId]
     * @return: java.util.List<com.xupt.community.domain.Member>
     * @author: lb
     * @time: 2021/4/8 12:17 上午
     */
    @RequestMapping("collectedMembers")
    public List<Member> getMembersByInformationId(Long informationId) {
        if (informationId == null) {
            return new ArrayList<>();
        }
        List<Collections> collectionList = collectionService.getMemberIdsByInformationId(informationId);
        List<Long> memberIds = PropertyExtractUtils.getByPropertyValue(collectionList, "memberId", Long.class);
        List<Member> result = new ArrayList<>();
        for (Long memberId : memberIds) {
            Member member = memberService.getById(memberId);
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
