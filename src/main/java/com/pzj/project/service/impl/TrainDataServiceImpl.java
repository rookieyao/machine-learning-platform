package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.TrainDataVersionVo;
import com.pzj.project.dto.TrainDataDTO;
import com.pzj.project.mapper.TrainDataMapper;
import com.pzj.project.entity.TrainData;
import com.pzj.project.model.User;
import com.pzj.project.service.TrainDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (TrainData)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
@Service("trainDataService")
public class TrainDataServiceImpl extends ServiceImpl<TrainDataMapper, TrainData> implements TrainDataService {


    @Resource
    private TrainDataMapper trainDataMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            trainDataMapper.deleteBatchIds(idList);
        }
    }

    @Override
    public List<TrainDataVersionVo> listPage(User user, TrainDataDTO trainDataDTO) {
        String userId = user.getUserId();
        List<TrainDataVersionVo> trainDataVersionVoList = trainDataMapper.listPage(userId, trainDataDTO);
        return trainDataVersionVoList;
    }


}


