package com.xupt.community.controller;

import com.xupt.community.domain.Information;
import com.xupt.community.service.InformationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * @description:最新动态
     * @params: []
     * @return: java.util.List<com.xupt.community.domain.Information>
     * @author: lb
     * @time: 2021/4/8 12:18 上午
     */
    @RequestMapping(value = "getLatestInformations", method = RequestMethod.GET)
    public List<Information> getLatestInformations() {
        List<Information> result = informationService.getLatestInformations();
        if (CollectionUtils.isNotEmpty(result)) {
            return result;
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
            return result;
        } else {
            return new ArrayList<>();
        }
    }

}
