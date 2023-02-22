package com.pzj.project.dto;



import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;
import com.pzj.project.common.CommonPage;

/**
 * (TrainDataLabelingTask)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Data
@SuppressWarnings("serial")
public class TrainDataLabelingTaskDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**训练数据版本id*/
    private Long trainDataVersionId;
    /**标注任务*/
    private String labelingTaskId;
    
    private String labelTaskName;
    
    private String labelTaskType;

}
