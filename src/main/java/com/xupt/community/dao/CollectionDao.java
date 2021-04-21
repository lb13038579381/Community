package com.xupt.community.dao;

import com.xupt.community.domain.Collections;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionDao {
    List<Collections> getCollectionsByMemberId(Long memberId);
    List<Collections> getMemberIdsByInformationId(Long informationId);
}
