package com.pzj.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.mapper.OriginDataMapper;
import com.pzj.project.entity.OriginData;
import com.pzj.project.model.OriginDataModel;
import com.pzj.project.service.OriginDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (OriginData)�����ʵ����
 *
 * @author makejava
 * @since 2023-02-18 12:11:12
 */
@Service("originDataService")
public class OriginDataServiceImpl extends ServiceImpl<OriginDataMapper, OriginData> implements OriginDataService {


    @Resource
    private OriginDataMapper originDataMapper;
    
    /**
     * ����idsɾ����������
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            originDataMapper.deleteBatchIds(idList);
        }
    }

    /**
     * 查找原始数据名称
     * @return
     */
    @Override
    public ArrayList<OriginDataModel> getByOriginData(OriginDataDTO originDataDTO) {
        Integer result_pageNo = originDataDTO.getPageSize();
        Integer result_pageSize = originDataDTO.getPageSize();
        Integer result = (result_pageNo-1)*result_pageSize;
        originDataDTO.setPageNum(result);
        return originDataMapper.getByOriginData(originDataDTO);
    }
    
    
}


