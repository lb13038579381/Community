package com.xupt.community.dao;

import com.xupt.community.domain.Collection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionDao {
    List<Collection> getCollectionsByMemberId(Long memberId);
    List<Collection> getMemberIdsByInformationId(Long informationId);
}
