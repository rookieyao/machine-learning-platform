package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.mapper.TrainModelMapper;
import com.pzj.project.entity.TrainModel;
import com.pzj.project.service.TrainModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (TrainModel)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Service("trainModelService")
public class TrainModelServiceImpl extends ServiceImpl<TrainModelMapper, TrainModel> implements TrainModelService {


    @Resource
    private TrainModelMapper trainModelMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            trainModelMapper.deleteBatchIds(idList);
        }
    }
    
    
}


