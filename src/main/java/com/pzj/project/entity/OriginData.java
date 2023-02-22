package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * (OriginData)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 12:11:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "table:origin_data")
public class OriginData {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 原始数据名称
     **/
    private String originDataName;
    
    /**
     * 文件大小，单位KB
     **/
    private String fileSize;
    
    /**
     * 处理状态 0处理中 1处理成功 2处理失败
     **/
    private Integer processState;
    
    /**
     * 原始数据文件路径
     **/
    private String originFilePath;
    
    /**
     * 语种 1中文 2英文
     **/
    private Integer language;
    
    /**
     * 数据源
     **/
    private String dataSource;
    
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
     * 删除标识 0未删除 1已删除
     **/
    private Integer isDelete;
    
}


