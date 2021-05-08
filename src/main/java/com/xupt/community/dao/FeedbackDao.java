package com.xupt.community.dao;

import com.xupt.community.domain.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedbackDao {
    void add(Feedback feedback);
    List<Feedback> list();

    void deleteById(Long id);
}
