package com.xupt.community.controller;

import com.xupt.community.domain.Community;
import com.xupt.community.domain.CommunityApply;
import com.xupt.community.dto.CommunityApplyDto;
import com.xupt.community.exception.FrontException;
import com.xupt.community.service.CommunityApplyService;
import com.xupt.community.service.CommunityService;
import com.xupt.community.util.PropertyExtractUtils;
import com.xupt.community.vo.CommunityApplyVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 社团申请controller
 * @author: lb
 * @time: 2021/3/28 1:37 上午
 */
@RequestMapping("communityApplyController")
@Slf4j
@Controller
@Resource
public class CommunityApplyController {
    private static Logger logger = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    CommunityApplyService communityApplyService;

    @Autowired
    CommunityService communityService;

    //我的申请
    @RequestMapping("getCommunityApplyListByMemberId")
    public List<CommunityApplyVo> getCommunityApplyListByMemberId(Long memberId) {
        if (memberId == null || memberId.toString().length() == 0) {
            throw new FrontException("用户id不能为空");
        }
        List<CommunityApply> communityApplyList = communityApplyService.getCommunityAppliesByMemberId(memberId);
        List<Long> communityIds = PropertyExtractUtils.getByPropertyValue(communityApplyList, "communityId");
        List<CommunityApplyVo> result = new ArrayList<>();
        List<Community> communityList = communityService.getByIds(communityIds);
        Map<Long, Community> communityIdAndCommunity = new HashMap<>();
        for (Community community : communityList) {
            communityIdAndCommunity.put(community.getId(), community);
        }
        for (CommunityApply communityApply : communityApplyList) {
            CommunityApplyVo vo = new CommunityApplyVo();
            vo.setApply(communityApply.getApply());
            vo.setCommunityName(communityIdAndCommunity.get(communityApply.getCommunityId()).getName());
            vo.setStatus(communityApply.getStatus());
            result.add(vo);
        }
        return result;
    }

    //新建申请
    @RequestMapping("addCommunityApply")
    public Boolean addCommunityApply(Long memberId, Long communityId) {
        CommunityApply apply = new CommunityApply();
        apply.setCommunityId(communityId);
        apply.setMemberId(memberId);
        try {
            communityApplyService.add(apply);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
