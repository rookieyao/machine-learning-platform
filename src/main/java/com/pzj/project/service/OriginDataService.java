package com.pzj.project.service;

import com.pzj.project.model.OriginDataModel;

import java.util.ArrayList;

public interface OriginDataService {
    /**
     * 查找原始数据名称
     * @return
     */
    ArrayList<OriginDataModel> getByOriginData();
//    /**
//     * 查找处理状态
//     * @return
//     */
//    ArrayList<OriginDataModel> getByProcessState();
}
