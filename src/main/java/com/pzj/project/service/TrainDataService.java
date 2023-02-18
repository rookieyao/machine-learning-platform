package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.TrainDataVersionVo;
import com.pzj.project.dto.TrainDataDTO;
import com.pzj.project.entity.TrainData;
import com.pzj.project.model.User;

import java.util.List;

/**
 * (TrainData)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
public interface TrainDataService extends IService<TrainData> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);

    List<TrainDataVersionVo> listPage(User user, TrainDataDTO trainDataDTO);
    
    
}


