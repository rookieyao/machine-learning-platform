package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.mapper.ProjectServeVersionTrainModelVersionMapper;
import com.pzj.project.entity.ProjectServeVersionTrainModelVersion;
import com.pzj.project.service.ProjectServeVersionTrainModelVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (ProjectServeVersionTrainModelVersion)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:40:53
 */
@Service("projectServeVersionTrainModelVersionService")
public class ProjectServeVersionTrainModelVersionServiceImpl extends ServiceImpl<ProjectServeVersionTrainModelVersionMapper, ProjectServeVersionTrainModelVersion> implements ProjectServeVersionTrainModelVersionService {


    @Resource
    private ProjectServeVersionTrainModelVersionMapper projectServeVersionTrainModelVersionMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            projectServeVersionTrainModelVersionMapper.deleteBatchIds(idList);
        }
    }
    
    
}


