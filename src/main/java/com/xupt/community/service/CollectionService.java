package com.xupt.community.service;

import com.xupt.community.domain.Collection;

import java.util.List;

public interface CollectionService {
    List<Collection> getCollectionsByMemberId(Long memberId);
    List<Collection> getMemberIdsByInformationId(Long informationId);
}
