package com.pzj.project.dto;



import java.io.Serializable;

import com.pzj.project.common.CommonPage;
import lombok.Data;
import java.sql.Timestamp;

/**
 * (OriginData)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 12:11:12
 */
@Data
@SuppressWarnings("serial")
public class OriginDataDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**原始数据名称*/
    private String originDataName;
    /**文件大小，单位KB*/
    private String fileSize;
    /**处理状态 0处理中 1处理成功 2处理失败*/
    private Integer processState;
    /**原始数据文件路径*/
    private String originFilePath;
    /**语种 1中文 2英文*/
    private Integer language;
    /**数据源*/
    private String dataSource;
    /**备注*/
    private String remark;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Timestamp createTime;
    /**更新人*/
    private String updator;
    /**更新时间*/
    private Timestamp updateTime;
    /**删除标识 0未删除 1已删除*/
    private Integer isDelete;

}
