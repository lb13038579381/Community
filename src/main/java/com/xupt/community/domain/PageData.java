package com.xupt.community.domain;

import lombok.Data;

@Data
public class PageData {
    private Integer currentPage;
    private Integer count;
    private Integer pageSize;
}
