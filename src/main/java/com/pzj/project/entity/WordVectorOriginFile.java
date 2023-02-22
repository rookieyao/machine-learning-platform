package com.pzj.project.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import lombok.NoArgsConstructor;

/**
 * (WordVectorOriginFile)��ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("")
@TableName(value = "table:word_vector_origin_file")
public class WordVectorOriginFile {
    /**
     * 主键id
     **/
    private Long id;
    
    /**
     * 词向量id
     **/
    private Long wordVectorId;
    
    /**
     * 原始语料文件
     **/
    private String originFile;
    
}


