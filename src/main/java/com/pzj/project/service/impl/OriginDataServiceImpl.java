package com.pzj.project.service.impl;

import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.mapper.OriginDataMapper;
import com.pzj.project.model.OriginAppendDataModel;
import com.pzj.project.model.OriginDataModel;
import com.pzj.project.service.OriginDataService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@Service
public class OriginDataServiceImpl implements OriginDataService {

    @Resource
    private OriginDataMapper originDataMapper;
    /**
     * 查找原始数据名称
     * @return
     */
    @Override
    public ArrayList<OriginDataModel> getByOriginData(OriginDataDTO originDataDTO) {
        Integer result_pageNo = originDataDTO.getPageNo();
        Integer result_pageSize = originDataDTO.getPageSize();
        Integer result = (result_pageNo-1)*result_pageSize;
        originDataDTO.setPageNo(result);
        return originDataMapper.getByOriginData(originDataDTO);
    }

}
