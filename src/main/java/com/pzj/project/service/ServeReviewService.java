package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.entity.ServeReview;

/**
 * (ServeReview)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 20:40:54
 */
public interface ServeReviewService extends IService<ServeReview> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);
    
    
}


