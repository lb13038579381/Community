package com.xupt.community.dao;

import com.xupt.community.domain.Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InformationDao {
    List<Information> getLatestInformations();
    List<Information> getByIds(List<Long> informationIds);

    void addInformation(Information information);

    List<Information> list();

    List<Information> getByCommunityId(Long communityId);

    void addCount(Long informationId);

    List<Information> getByCommunityIds(List<Long> communityIds);
}
