package com.xupt.community.service.impl;

import com.xupt.community.dao.InformationDao;
import com.xupt.community.domain.Information;
import com.xupt.community.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    InformationDao informationDao;
    @Override
    public List<Information> getLatestInformations() {
        return informationDao.getLatestInformations();
    }

    @Override
    public List<Information> getByIds(List<Long> informationIds) {
        return informationDao.getByIds(informationIds);
    }

    @Override
    public void addInformation(Information information) {
        informationDao.addInformation(information);
    }

    @Override
    public List<Information> list() {
        return informationDao.list();
    }

    @Override
    public List<Information> getByCommunityId(Long communityId) {
        return informationDao.getByCommunityId(communityId);
    }

    @Override
    public void addCount(Long informationId) {
        informationDao.addCount(informationId);
    }

    @Override
    public List<Information> getByCommunityIds(List<Long> communityIds) {
        return informationDao.getByCommunityIds(communityIds);
    }

    @Override
    public List<Information> hot() {
        return informationDao.hot();
    }
}
