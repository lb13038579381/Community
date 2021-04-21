package com.xupt.community.service.impl;

import com.xupt.community.dao.CollectionDao;
import com.xupt.community.domain.Collections;
import com.xupt.community.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionDao collectionDao;
    @Override
    public List<Collections> getCollectionsByMemberId(Long memberId) {
        return collectionDao.getCollectionsByMemberId(memberId);
    }

    @Override
    public List<Collections> getMemberIdsByInformationId(Long informationId) {
        return collectionDao.getMemberIdsByInformationId(informationId);
    }
}
