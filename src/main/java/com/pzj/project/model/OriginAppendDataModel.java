package com.pzj.project.model;

import lombok.Data;

@Data
public class OriginAppendDataModel {
    /**
     * 原始数据追加id
     */
    private Long id;
    /**
     * 原始追加文件路径
     */
    private String originAppendFilePath;
    /**
     * 原始数据id
     */
    private Long originDataID;
    /**
     * 原始追加文件大小
     */
    private Double originAppendFileSize;
    /**
     * 行数
     */
    private Integer lineNum;
}
