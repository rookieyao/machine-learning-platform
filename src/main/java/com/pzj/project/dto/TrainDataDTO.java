package com.pzj.project.dto;


import com.pzj.project.common.CommonPage;
import lombok.Data;

import java.io.Serializable;

/**
 * (TrainData)��ʵ����
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
@Data
@SuppressWarnings("serial")
public class TrainDataDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**数据集名称*/
    private String dataCollectionName;
    /**标签语种 1中文 2英文*/
    private Integer tagLanguage;
    /**项目类型 1产品洞察 2服务洞察*/
    private Integer projectType;

}
