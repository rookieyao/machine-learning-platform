package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;

/**
 * (ProjectServeVersionTrainModelVersion)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "table:project_serve_version_train_model_version")
public class ProjectServeVersionTrainModelVersion {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 项目服务版本id
     **/
    private Long projectServeVersionId;
    
    /**
     * 训练模型版本id
     **/
    private Long trainModelVersionId;
    
}


