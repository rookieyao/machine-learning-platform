package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.entity.TrainModelVersion;

/**
 * (TrainModelVersion)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
public interface TrainModelVersionService extends IService<TrainModelVersion> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);
    
    
}


