package com.pzj.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "origin_append_data_file")
public class OriginAppendDataFile {
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
    private Long originDataId;
    /**
     * 原始追加文件大小
     */
    private Double originAppendFileSize;
    /**
     * 行数
     */
    private Integer lineNum;
}
