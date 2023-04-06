package com.amx.material.dto;

import lombok.Data;

/**
 * @author lin
 * @date 2022年06月28日 17:17
 */
@Data
public class BaseDto {

    //时间粒度 ：day month
    private String timeGranularity;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private int currentPage;
    private int pageSize;
    //正序 倒序
    private String order;
    //排序字段
    private String property;

}
