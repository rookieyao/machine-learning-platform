package com.pzj.project.dto;



import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;
import com.pzj.project.common.CommonPage;

/**
 * (WordVectorOriginFile)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Data
@SuppressWarnings("serial")
public class WordVectorOriginFileDTO extends CommonPage implements Serializable {
  
    /**主键id*/
    private Long id;
    /**词向量id*/
    private Long wordVectorId;
    /**原始语料文件*/
    private String originFile;

}
