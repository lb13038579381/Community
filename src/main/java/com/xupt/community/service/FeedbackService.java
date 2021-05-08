package com.xupt.community.service;

import com.xupt.community.domain.Feedback;

import java.util.List;

public interface FeedbackService {
    void add(Feedback feedback);
    List<Feedback> list();

    void deleteById(Long id);
}
