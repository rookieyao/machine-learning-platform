package com.pzj.project.service;

import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.model.OriginAppendDataModel;
import com.pzj.project.model.OriginDataModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public interface OriginDataService {
    /**
     * 查找原始数据名称
     * @return
     */
    ArrayList<OriginDataModel> getByOriginData(OriginDataDTO originDataDTO);
}
