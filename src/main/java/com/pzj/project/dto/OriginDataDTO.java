package com.pzj.project.dto;

import com.pzj.project.common.CommonPage;
import lombok.Data;

import java.io.Serializable;

@Data
public class OriginDataDTO extends CommonPage implements Serializable {
    /**
     * 原始数据名称
     */
    private String originDataName;
    /**
     * 处理状态
     */
    private Integer processState;
    /**
     * 当前页面编号
     */
    private Integer pageNo;
    /**
     * 每页最大行数
     */
    private Integer pageSize;
}
