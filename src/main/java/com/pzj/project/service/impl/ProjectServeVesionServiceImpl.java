package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.mapper.ProjectServeVesionMapper;
import com.pzj.project.entity.ProjectServeVesion;
import com.pzj.project.service.ProjectServeVesionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (ProjectServeVesion)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:54
 */
@Service("projectServeVesionService")
public class ProjectServeVesionServiceImpl extends ServiceImpl<ProjectServeVesionMapper, ProjectServeVesion> implements ProjectServeVesionService {


    @Resource
    private ProjectServeVesionMapper projectServeVesionMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            projectServeVesionMapper.deleteBatchIds(idList);
        }
    }
    
    
}


