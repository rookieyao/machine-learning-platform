package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.entity.ProjectServe;

/**
 * (ProjectServe)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
public interface ProjectServeService extends IService<ProjectServe> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);
    
    
}


