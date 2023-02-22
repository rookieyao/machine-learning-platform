package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.mapper.TrainDataLabelingTaskMapper;
import com.pzj.project.entity.TrainDataLabelingTask;
import com.pzj.project.service.TrainDataLabelingTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (TrainDataLabelingTask)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Service("trainDataLabelingTaskService")
public class TrainDataLabelingTaskServiceImpl extends ServiceImpl<TrainDataLabelingTaskMapper, TrainDataLabelingTask> implements TrainDataLabelingTaskService {


    @Resource
    private TrainDataLabelingTaskMapper trainDataLabelingTaskMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            trainDataLabelingTaskMapper.deleteBatchIds(idList);
        }
    }
    
    
}


