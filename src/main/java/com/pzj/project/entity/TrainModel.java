package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;

/**
 * (TrainModel)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "table:train_model")
public class TrainModel {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 项目名称
     **/
    private String projectName;
    
    /**
     * 项目类型 1产品洞察 2服务洞察
     **/
    private Integer projectType;
    
    /**
     * 模型描述
     **/
    private String modelDescribe;
    
    /**
     * 模型评估信息
     **/
    private String modelEvaluateInfo;
    
}


