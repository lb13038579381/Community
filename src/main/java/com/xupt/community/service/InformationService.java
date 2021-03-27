package com.xupt.community.service;

import com.xupt.community.domain.Information;

import java.util.List;

public interface InformationService {
    List<Information> getLatestInformations();
}
