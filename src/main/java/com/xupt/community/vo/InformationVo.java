package com.xupt.community.vo;

import com.xupt.community.domain.Information;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class InformationVo {
    private Long id;
    private String title;
    private String text;
    private Long time;
    private String address;
    private String covPicUrl;
    private Long posterId;
    private Long communityId;
    private Long gmtCreate;
    private String timeStr;
    private String communityName;
    private Long collect;
    private Boolean apply;
    private Long startTime;
    private Long endTime;
    private String startTimeStr;
    private String endTimeStr;
    private Integer status;
    private Integer length;
    private Integer count;
    private Integer people;
    private Integer type;
    private String thumbnail;
    public static InformationVo convert(Information information) {
        InformationVo vo = new InformationVo();
        vo.setTitle(information.getTitle());
        vo.setText(information.getText());
        vo.setTime(information.getTime());
        vo.setAddress(information.getAddress());
        vo.setCovPicUrl(information.getCovPicUrl());
        vo.setPosterId(information.getPosterId());
        vo.setCommunityId(information.getCommunityId());
        vo.setGmtCreate(information.getGmtCreate());
        vo.setId(information.getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(information.getTime());
        String timeStr = simpleDateFormat.format(date);
        vo.setTimeStr(timeStr);
        date = new Date(information.getStartTime());
        String startTimeStr = simpleDateFormat.format(date);
        vo.setStartTimeStr(startTimeStr);
        date = new Date(information.getEndTime());
        String endTimeStr = simpleDateFormat.format(date);
        vo.setEndTimeStr(endTimeStr);
        vo.setStartTime(information.getStartTime());
        vo.setEndTime(information.getEndTime());
        vo.setCount(information.getCount());
        vo.setPeople(information.getPeople());
        vo.setThumbnail(information.getThumbnail());
        return vo;
    }
}
