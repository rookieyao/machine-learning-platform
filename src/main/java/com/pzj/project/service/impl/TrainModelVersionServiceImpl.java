package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.mapper.TrainModelVersionMapper;
import com.pzj.project.entity.TrainModelVersion;
import com.pzj.project.service.TrainModelVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (TrainModelVersion)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@Service("trainModelVersionService")
public class TrainModelVersionServiceImpl extends ServiceImpl<TrainModelVersionMapper, TrainModelVersion> implements TrainModelVersionService {


    @Resource
    private TrainModelVersionMapper trainModelVersionMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            trainModelVersionMapper.deleteBatchIds(idList);
        }
    }
    
    
}


