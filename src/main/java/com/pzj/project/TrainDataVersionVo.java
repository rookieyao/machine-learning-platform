package com.pzj.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author rookie
 * @Date 2023-02-18 14:10
 * @Description
 **/
@Data
public class TrainDataVersionVo {
    // 版本主键id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    // 主表主键id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long trainDataId;

    // 数据集名称
    private String dataCollectionName;


    // 项目类型
    private Integer projectType;

    // 语种
    private Integer labelLanguage;

    // 导入状态
    private Integer importState;

    // 版本号
    private String dataSetVersion;

    // 版本名称
    private String dataSetVersionName;

    // 数据类型
    private String dataType;

    // 备注
    private String remark;

    // 创建人
    private String creator;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @JsonIgnore
    private String labelTaskGroupStr;

    /**
     * 错误日志
     */
    private String errorLog;

    private String overTagFilePath;

    /**
     * 训练数据文件目录
     */
    private String dataSetVersionFilePath;

    /**
     * 指标文件路径
     */
    private String tagFilePath;

    /**
     * 指标文件excel路径
     */
    private String tagExcelFilePath;
}
