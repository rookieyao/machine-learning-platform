package com.pzj.project.service;

import com.pzj.project.model.OriginAppendDataModel;
import com.pzj.project.model.OriginDataModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface OriginDataService {
    /**
     * 查找原始数据名称
     * @return
     */
    ArrayList<OriginDataModel> getByOriginData(String originDataName,Integer processState,Integer pageNum,Integer lineNum);
}
