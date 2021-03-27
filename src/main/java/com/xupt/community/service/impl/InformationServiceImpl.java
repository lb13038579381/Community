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
}
