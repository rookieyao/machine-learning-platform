package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.entity.ProjectServeVesion;

/**
 * (ProjectServeVesion)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 20:40:54
 */
public interface ProjectServeVesionService extends IService<ProjectServeVesion> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);
    
    
}


