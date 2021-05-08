package com.xupt.community.dao;

import com.xupt.community.domain.CommunityApply;
import com.xupt.community.domain.NewCommunityApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewCommunityApplyDao {
    void add(NewCommunityApply newCommunityApply);
    List<NewCommunityApply> list();
    List<NewCommunityApply> getByMemberId(Long memberId);

    void deleteById(Long id);
    void refuseApply(Long id);
    void adoptApply(Long id);
    NewCommunityApply getById(Long id);
}
