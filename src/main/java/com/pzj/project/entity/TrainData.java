package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;

/**
 * (TrainData)��ʵ����
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "train_data")
public class TrainData {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 数据集名称
     **/
    private String dataCollectionName;
    
    /**
     * 标签语种 1中文 2英文
     **/
    private Integer tagLanguage;
    
    /**
     * 项目类型 1产品洞察 2服务洞察
     **/
    private Integer projectType;
    
}


