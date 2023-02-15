package com.pzj.project.service.impl;

import com.pzj.project.mapper.OriginDataMapper;
import com.pzj.project.model.OriginDataModel;
import com.pzj.project.service.OriginDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class OriginDataServiceImpl implements OriginDataService {

    @Resource
    private OriginDataMapper originDataMapper;
    /**
     * 查找原始数据名称
     * @return
     */
    @Override
    public ArrayList<OriginDataModel> getByOriginData() {
        return originDataMapper.getByOriginData();
    }

//    /**
//     * 查找处理状态
//     * @return
//     */
//    @Override
//    public ArrayList<OriginDataModel> getByProcessState(){
//        return originDataMapper.getByOriginDataName();
//    }
}
