package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.entity.ProjectServeVersionTrainModelVersion;

/**
 * (ProjectServeVersionTrainModelVersion)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
public interface ProjectServeVersionTrainModelVersionService extends IService<ProjectServeVersionTrainModelVersion> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);
    
    
}


