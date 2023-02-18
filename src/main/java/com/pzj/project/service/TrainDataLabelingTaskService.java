package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.entity.TrainDataLabelingTask;

/**
 * (TrainDataLabelingTask)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
public interface TrainDataLabelingTaskService extends IService<TrainDataLabelingTask> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);
    
    
}


