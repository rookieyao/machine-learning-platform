package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.entity.WordVector;

/**
 * (WordVector)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 20:34:16
 */
public interface WordVectorService extends IService<WordVector> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);
    
    
}


