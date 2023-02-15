package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.mapper.TrainDataVersionMapper;
import com.pzj.project.entity.TrainDataVersion;
import com.pzj.project.service.TrainDataVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (TrainDataVersion)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
@Service("trainDataVersionService")
public class TrainDataVersionServiceImpl extends ServiceImpl<TrainDataVersionMapper, TrainDataVersion> implements TrainDataVersionService {


    @Resource
    private TrainDataVersionMapper trainDataVersionMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            trainDataVersionMapper.deleteBatchIds(idList);
        }
    }
    
    
}


