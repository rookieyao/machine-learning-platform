package com.pzj.project.dto;



import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;
import com.pzj.project.common.CommonPage;

/**
 * (TrainDataVersion)��ʵ����
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
@Data
@SuppressWarnings("serial")
public class TrainDataVersionDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**训练数据id*/
    private Long trainDataId;
    /**导入状态 1导入中 2导入成功 3导入失败*/
    private Integer importState;
    /**数据集版本*/
    private String dataCollectionVersion;
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
