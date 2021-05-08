package com.xupt.community.controller;

import com.xupt.community.domain.Feedback;
import com.xupt.community.service.FeedbackService;
import com.xupt.community.vo.CodeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("feedbackController")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @RequestMapping("feedbackList")
    public List<Feedback> getFeedbackList() {
        List<Feedback> result = feedbackService.list();
        if(CollectionUtils.isNotEmpty(result)) {
            return result;
        }else{
            return new ArrayList<>();
        }
    }

    @RequestMapping("deleteFeedback")
    public CodeVo deleteFeedback(Long id) {
        CodeVo vo = new CodeVo();
        try {
            feedbackService.deleteById(id);
            vo.setErrorCode(0);
            return vo;
        }catch (Exception e) {
            vo.setErrorCode(-1);
            return vo;
        }
    }
}
