package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.entity.TrainData;

/**
 * (TrainData)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
public interface TrainDataService extends IService<TrainData> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);
    
    
}


