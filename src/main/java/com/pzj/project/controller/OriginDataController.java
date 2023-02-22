package com.pzj.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.ResponseResult;
import com.pzj.project.common.Result;
import com.pzj.project.dto.OriginDataDTO;
import com.pzj.project.entity.OriginData;
import com.pzj.project.entity.TrainData;
import com.pzj.project.service.OriginDataService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.hibernate.boot.jaxb.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author rookie
 * @Date 2023-02-08 20:51
 * @Description
 **/
@CrossOrigin
@RestController
@RequestMapping("/originData")
public class OriginDataController extends ResponseResult {
    @Resource
    private OriginDataService originDataService;

    @PostMapping("/listPage")
    public Result<?> getByOriginDataName(@RequestBody(required = false) OriginDataDTO originDataDTO){

        PageHelper.startPage(originDataDTO.getPageNum(),originDataDTO.getPageSize());
        //������ѯ
        QueryWrapper<OriginData> queryWrapper = new QueryWrapper();

        String originDataName = originDataDTO.getOriginDataName();
        if(StringUtils.isNotBlank(originDataName)){
            queryWrapper.lambda().eq(OriginData::getOriginDataName, originDataName);
        }

        Integer processState = originDataDTO.getProcessState();
        if(processState != null){
            queryWrapper.lambda().eq(OriginData::getProcessState, processState.intValue());
        }

        //����
        // queryWrapper.lambda().orderByDesc(TrainData::getCreateTime);
        //ִ�в�ѯ
        List<OriginData> list = originDataService.list(queryWrapper);
        PageInfo<OriginData> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }

    @DeleteMapping("/delData/{id}")
    public Result<?> delById (@PathVariable("id") Long id){
        return originDataService.delById(id) > 0 ? Result.success() : Result.error("删除数据失败");
    }

    @PostMapping("/insetData")
    public Result<?> insertData(OriginData originData){
        return Result.success(originDataService.insertData(originData));
    }

}
