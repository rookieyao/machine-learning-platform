package com.pzj.project.service.impl;

import com.pzj.project.mapper.OriginDataMapper;
import com.pzj.project.model.OriginAppendDataModel;
import com.pzj.project.model.OriginDataModel;
import com.pzj.project.service.OriginDataService;
import org.apache.ibatis.annotations.Param;
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
    public ArrayList<OriginDataModel> getByOriginData(String originDataName,Integer processState,Integer pageNum,Integer lineNum) {
        return originDataMapper.getByOriginData(originDataName,processState);
    }

}
