package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;

/**
 * (TrainDataLabelingTask)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "table:train_data_labeling_task")
public class TrainDataLabelingTask {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 训练数据版本id
     **/
    private Long trainDataVersionId;
    
    /**
     * 标注任务
     **/
    private String labelingTaskId;
    
    private String labelTaskName;
    
    private String labelTaskType;
    
}


