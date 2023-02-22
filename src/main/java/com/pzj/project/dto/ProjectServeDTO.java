package com.pzj.project.dto;



import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;
import com.pzj.project.common.CommonPage;

/**
 * (ProjectServe)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
@Data
@SuppressWarnings("serial")
public class ProjectServeDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**服务名称*/
    private String serveName;
    /**项目名称*/
    private String projectName;
    /**项目类型 1产品洞察 2服务洞察*/
    private Integer projectType;
    /**配置信息*/
    private String congigurationInfo;

}
