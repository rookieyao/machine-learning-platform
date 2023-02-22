package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;

/**
 * (ProjectServe)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "table:project_serve")
public class ProjectServe {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 服务名称
     **/
    private String serveName;
    
    /**
     * 项目名称
     **/
    private String projectName;
    
    /**
     * 项目类型 1产品洞察 2服务洞察
     **/
    private Integer projectType;
    
    /**
     * 配置信息
     **/
    private String congigurationInfo;
    
}


