package com.pzj.project.service;

import com.pzj.project.model.OriginDataModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface OriginDataService {
    /**
     * 查找原始数据名称
     * @return
     */
    ArrayList<OriginDataModel> getByOriginData(String originDataName,Byte precessState,int pageNum,int lineNum);
//    /**
//     * 查找处理状态
//     * @return
//     */
//    ArrayList<OriginDataModel> getByProcessState();
}
