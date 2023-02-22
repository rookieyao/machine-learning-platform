package com.pzj.project.dto;



import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;
import com.pzj.project.common.CommonPage;

/**
 * (ServeReview)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:54
 */
@Data
@SuppressWarnings("serial")
public class ServeReviewDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**服务名称*/
    private String serveName;
    /**服务版本*/
    private String serveVersion;
    /**审核状态 1待审核 2已通过 3已拒绝*/
    private Integer reviewState;
    /**审核备注*/
    private String reviewRemark;
    /**审核人*/
    private String reviewer;
    /**审核时间*/
    private Timestamp reviewTime;

}
