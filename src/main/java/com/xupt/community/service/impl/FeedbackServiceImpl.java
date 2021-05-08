package com.xupt.community.service.impl;

import com.xupt.community.dao.FeedbackDao;
import com.xupt.community.domain.Feedback;
import com.xupt.community.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackDao feedbackDao;
    @Override
    public void add(Feedback feedback) {
        feedbackDao.add(feedback);
    }

    @Override
    public List<Feedback> list() {
        return feedbackDao.list();
    }

    @Override
    public void deleteById(Long id) {
        feedbackDao.deleteById(id);
    }
}
