package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;

/**
 * (WordVector)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:34:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "table:word_vector")
public class WordVector {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 字词向量名称
     **/
    private String wordVectorName;
    
    /**
     * 向量维度
     **/
    private Integer vectorDimension;
    
    /**
     * 窗口大小
     **/
    private Integer windowSize;
    
    /**
     * 最小词频
     **/
    private Integer minimumWordFrequency;
    
    /**
     * 向量类型 0无 1字 2词 3字和词
     **/
    private Integer vectorType;
    
    /**
     * 备注
     **/
    private String remark;
    
    /**
     * 任务状态 1处理中 2处理成功 3处理失败
     **/
    private Integer taskState;
    
    /**
     * 当前处理进度
     **/
    private String currentProcessingProgress;
    
    /**
     * 数据是否最新 1 Y 2 N
     **/
    private Integer dataIsNew;
    
    /**
     * 创建人
     **/
    private String creator;
    
    /**
     * 创建时间
     **/
    private Timestamp createTime;
    
    /**
     * 更新人
     **/
    private String updator;
    
    /**
     * 更新时间
     **/
    private Timestamp updateTime;
    
    /**
     * 删除标识 0未删除 1已删除
     **/
    private Integer isDelete;
    
}


