package com.pzj.project.dto;



import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;
import com.pzj.project.common.CommonPage;

/**
 * (TrainModelVersion)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Data
@SuppressWarnings("serial")
public class TrainModelVersionDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**训练模型id*/
    private Long trainModelId;
    /**模型类型 0无 1指标 2情感*/
    private Integer modelType;
    /**模型版本*/
    private String modelVersion;
    /**字词向量名称*/
    private String wordVectorName;
    /**训练数据名称*/
    private String trainDataName;
    /**训练状态 1未训练 2训练中 3训练成功 4训练失败*/
    private Integer modelState;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Timestamp createTime;
    /**更新人*/
    private String updator;
    /**更新时间*/
    private Timestamp updaterTime;
    /**删除标识 0未删除 1已删除*/
    private Integer isDelete;

}
