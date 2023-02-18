package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.mapper.ProjectServeMapper;
import com.pzj.project.entity.ProjectServe;
import com.pzj.project.service.ProjectServeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (ProjectServe)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
@Service("projectServeService")
public class ProjectServeServiceImpl extends ServiceImpl<ProjectServeMapper, ProjectServe> implements ProjectServeService {


    @Resource
    private ProjectServeMapper projectServeMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            projectServeMapper.deleteBatchIds(idList);
        }
    }
    
    
}


