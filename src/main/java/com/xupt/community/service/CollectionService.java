package com.xupt.community.service;

import com.xupt.community.domain.Collections;

import java.util.List;

public interface CollectionService {
    List<Collections> getCollectionsByMemberId(Long memberId);
    List<Collections> getMemberIdsByInformationId(Long informationId);
}
