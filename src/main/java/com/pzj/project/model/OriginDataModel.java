package com.pzj.project.model;

import lombok.Data;

import java.util.Date;

@Data
public class OriginDataModel {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 原始数据名称
     */
    private String originDataName;
    /**
     * 文件大小,单位KB
     */
    private Double fileSize;
    /**
     * 处理状态 1处理中 2处理成功 3处理失败
     */
    private Integer process_state;
    /**
     * 原始数据文件路径
     */
    private String originFilePath;
    /**
     * 语种 1中文 2英文
     */
    private Integer language;
    /**
     * 数据源
     */
    private String dataSource;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 更新人
     */
    private String updator;
    /**
     * 更新时间
     */
    private Date update_time;
    /**
     * 删除标识
     */
    private Integer isDelete;
}
