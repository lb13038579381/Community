package com.xupt.community.vo;

import lombok.Data;

import java.util.List;

@Data
public class IVo {
    private Boolean isDirector;
    private List<InformationVo> informationVoList;
}
