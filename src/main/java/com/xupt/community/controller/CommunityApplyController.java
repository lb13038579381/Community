package com.xupt.community.controller;

import com.xupt.community.domain.Community;
import com.xupt.community.domain.CommunityApply;
import com.xupt.community.dto.CommunityApplyDto;
import com.xupt.community.exception.FrontException;
import com.xupt.community.service.CommunityApplyService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

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

    @RequestMapping("getCommunityApplyListByMemberId")
    public List<CommunityApplyDto> getCommunityApplyListByMemberId(Long memberId) {
        if(memberId == null || memberId.toString().length() == 0) {
            throw new FrontException("用户id不能为空");
        }
        List<CommunityApply> communityApplyList = communityApplyService.getCommunityAppliesByMemberId(memberId);

    }
}
