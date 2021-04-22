package com.xupt.community.service.impl;

import com.xupt.community.dao.InformationApplyDao;
import com.xupt.community.domain.InformationApply;
import com.xupt.community.service.InformationApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationApplyServiceImpl implements InformationApplyService {
    @Autowired
    InformationApplyDao informationApplyDao;
    @Override
    public InformationApply getByMemberIdAndInformationId(Long memberId, Long informationId) {
        InformationApply informationApply = new InformationApply();
        informationApply.setMemberId(memberId);
        informationApply.setInformationId(informationId);
        return informationApplyDao.getByMemberIdAndInformationId(informationApply);
    }

    @Override
    public List<InformationApply> getByMemberId(Long memberId) {
        return informationApplyDao.getByMemberId(memberId);
    }

    @Override
    public List<InformationApply> getByInformationId(Long informationId) {
        return informationApplyDao.getByInformationId(informationId);
    }

    @Override
    public void add(InformationApply informationApply) {
        informationApplyDao.add(informationApply);
    }
}
