package com.pzj.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.entity.OriginData;
import com.pzj.project.model.OriginDataModel;

import java.util.ArrayList;

/**
 * (OriginData)�����ӿ�
 *
 * @author makejava
 * @since 2023-02-18 12:11:12
 */
public interface OriginDataService extends IService<OriginData> {

    /**
     * ����idsɾ����������
     **/
    void deleteByIds(String ids);

    ArrayList<OriginDataModel> getByOriginData(OriginDataDTO originDataDTO);
    
}


