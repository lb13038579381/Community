package com.xupt.community.controller;

import com.xupt.community.domain.Information;
import com.xupt.community.service.InformationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
 * @time: 2021/3/28 1:21 上午
 */
@RequestMapping("information")
@Resource
@Controller
@Slf4j
public class InformationController {
    private static Logger logger = LoggerFactory.getLogger(InformationController.class);
    @Autowired
    InformationService informationService;

    /**
     * @description:最新动态
     * @params: []
     * @return: java.util.List<com.xupt.community.domain.Information>
     * @author: lb
     * @time: 2021/4/8 12:18 上午
     */
    @RequestMapping("getLatestInformations")
    public List<Information> getLatestInformations() {
        List<Information> result = informationService.getLatestInformations();
        if (CollectionUtils.isNotEmpty(result)) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

}
