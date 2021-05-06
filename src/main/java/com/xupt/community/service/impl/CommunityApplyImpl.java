package com.xupt.community.service.impl;

import com.xupt.community.dao.CommunityApplyDao;
import com.xupt.community.domain.CommunityApply;
import com.xupt.community.dto.CommunityApplyDto;
import com.xupt.community.dto.CommunityDto;
import com.xupt.community.service.CommunityApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityApplyImpl implements CommunityApplyService {
    @Autowired
    CommunityApplyDao communityApplyDao;
    @Override
    public void add(CommunityApply communityApply) {
        communityApplyDao.add(communityApply);
    }

    @Override
    public void delete(CommunityApply communityApply) {
        communityApplyDao.delete(communityApply);
    }

    @Override
    public List<CommunityApply> getCommunityAppliesByCommunityId(Long communityId) {
        return communityApplyDao.getCommunityAppliesByCommunityId(communityId);
    }

    @Override
    public List<CommunityApply> getCommunityAppliesByMemberId(Long memberId) {
        return communityApplyDao.getCommunityAppliesByMemberId(memberId);
    }

    @Override
    public CommunityApply getByMemberIdAndCommunityId(Long memberId, Long communityId) {
        CommunityApply apply = new CommunityApply();
        apply.setMemberId(memberId);
        apply.setCommunityId(communityId);
        return communityApplyDao.getByMemberIdAndCommunityId(apply);
    }

    @Override
    public void deleteById(Long id) {
        communityApplyDao.deleteById(id);
    }

    @Override
    public void refuseApply(Long id) {
        communityApplyDao.refuseApply(id);

    }

    @Override
    public void adoptApply(Long id) {
        communityApplyDao.adoptApply(id);

    }

    @Override
    public CommunityApply getById(Long id) {
        return communityApplyDao.getById(id);
    }
}
