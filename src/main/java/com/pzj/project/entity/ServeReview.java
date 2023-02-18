package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;

/**
 * (ServeReview)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "table:serve_review")
public class ServeReview {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 服务名称
     **/
    private String serveName;
    
    /**
     * 服务版本
     **/
    private String serveVersion;
    
    /**
     * 审核状态 1待审核 2已通过 3已拒绝
     **/
    private Integer reviewState;
    
    /**
     * 审核备注
     **/
    private String reviewRemark;
    
    /**
     * 审核人
     **/
    private String reviewer;
    
    /**
     * 审核时间
     **/
    private Timestamp reviewTime;
    
}


