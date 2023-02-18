package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.entity.TrainModel;

/**
 * (TrainModel)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
public interface TrainModelService extends IService<TrainModel> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);
    
    
}


