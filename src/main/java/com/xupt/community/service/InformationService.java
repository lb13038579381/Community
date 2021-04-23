package com.xupt.community.service;

import com.xupt.community.domain.Information;

import java.util.List;

public interface InformationService {
    List<Information> getLatestInformations();
    List<Information> getByIds(List<Long> informationIds);

    void addInformation(Information information);

    List<Information> list();

    List<Information> getByCommunityId(Long communityId);

    void addCount(Long informationId);

    List<Information> getByCommunityIds(List<Long> communityIds);
}
