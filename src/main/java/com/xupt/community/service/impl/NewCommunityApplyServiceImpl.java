package com.xupt.community.service.impl;

import com.xupt.community.dao.NewCommunityApplyDao;
import com.xupt.community.domain.CommunityApply;
import com.xupt.community.domain.NewCommunityApply;
import com.xupt.community.service.NewCommunityApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewCommunityApplyServiceImpl implements NewCommunityApplyService {
    @Autowired
    NewCommunityApplyDao newCommunityApplyDao;
    @Override
    public void add(NewCommunityApply newCommunityApply) {
        newCommunityApplyDao.add(newCommunityApply);
    }

    @Override
    public List<NewCommunityApply> list() {
        return newCommunityApplyDao.list();
    }

    @Override
    public List<NewCommunityApply> getByMemberId(Long memberId) {
        return newCommunityApplyDao.getByMemberId(memberId);
    }

    @Override
    public void deleteById(Long id) {
        newCommunityApplyDao.deleteById(id);
    }

    @Override
    public void refuseApply(Long id) {
        newCommunityApplyDao.refuseApply(id);
    }

    @Override
    public void adoptApply(Long id) {
        newCommunityApplyDao.adoptApply(id);
    }

    @Override
    public NewCommunityApply getById(Long id) {
        return newCommunityApplyDao.getById(id);
    }
}
