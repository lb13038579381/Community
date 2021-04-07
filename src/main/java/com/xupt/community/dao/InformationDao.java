package com.xupt.community.dao;

import com.xupt.community.domain.Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InformationDao {
    List<Information> getLatestInformations();
    List<Information> getByIds(List<Long> informationIds);
}
