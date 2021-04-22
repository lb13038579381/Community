package com.xupt.community.dao;

import com.xupt.community.domain.Information;
import com.xupt.community.domain.InformationApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InformationApplyDao {
    InformationApply getByMemberIdAndInformationId(InformationApply informationApply);
    List<InformationApply> getByMemberId(Long memberId);
    List<InformationApply> getByInformationId(Long informationId);
    void add(InformationApply informationApply);
}
