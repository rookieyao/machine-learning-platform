package com.pzj.project.dto;



import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;
import com.pzj.project.common.CommonPage;

/**
 * (TrainModel)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Data
@SuppressWarnings("serial")
public class TrainModelDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**项目名称*/
    private String projectName;
    /**项目类型 1产品洞察 2服务洞察*/
    private Integer projectType;
    /**模型描述*/
    private String modelDescribe;
    /**模型评估信息*/
    private String modelEvaluateInfo;

}
