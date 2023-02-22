package com.pzj.project.dto;



import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;
import com.pzj.project.common.CommonPage;

/**
 * (ProjectServeVersionTrainModelVersion)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
@Data
@SuppressWarnings("serial")
public class ProjectServeVersionTrainModelVersionDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**项目服务版本id*/
    private Long projectServeVersionId;
    /**训练模型版本id*/
    private Long trainModelVersionId;

}
