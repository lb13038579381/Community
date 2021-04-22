package com.xupt.community.service;

import com.xupt.community.domain.InformationApply;

import java.util.List;

public interface InformationApplyService {
    InformationApply getByMemberIdAndInformationId(Long memberId,Long informationId);
    List<InformationApply> getByMemberId(Long memberId);
    List<InformationApply> getByInformationId(Long informationId);
    void add(InformationApply informationApply);
}
