package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;

/**
 * (ProjectServeVesion)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "table:project_serve_vesion")
public class ProjectServeVesion {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 服务版本
     **/
    private String serveVersion;
    
    /**
     * 模型类型
     **/
    private String modelType;
    
    /**
     * 评估状态 1评估中 2评估成功 3评估失败
     **/
    private Integer evaluateState;
    
    /**
     * 服务状态  1已上线 2上线失败
     **/
    private Integer serveState;
    
    /**
     * 部署环境
     **/
    private String deployEnvironment;
    
    /**
     * 备注
     **/
    private String remark;
    
    /**
     * 创建人
     **/
    private String creator;
    
    /**
     * 创建时间
     **/
    private Timestamp createTime;
    
    /**
     * 更新人
     **/
    private String updator;
    
    /**
     * 更新时间
     **/
    private Timestamp updateTime;
    
    /**
     * 删除表示 0未删除 1已删除
     **/
    private Integer isDelete;
    
}


