package com.pzj.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.TrainDataVersionVo;
import com.pzj.project.common.Result;
import com.pzj.project.common.annotation.CurrentUser;
import com.pzj.project.common.enums.ErrorCodeEnum;
import com.pzj.project.dto.TrainDataDTO;
import com.pzj.project.entity.TrainData;
import com.pzj.project.model.User;
import com.pzj.project.service.TrainDataService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TrainData)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
@RestController
@RequestMapping("/trainData")
public class TrainDataController {
    /**
     * �������
     */
    @Autowired
    private TrainDataService trainDataService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody TrainDataDTO trainDataDTO) {
        //������ѯ
        QueryWrapper<TrainData> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(TrainData::getCreateTime);
        //ִ�в�ѯ
        List<TrainData> list = trainDataService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@CurrentUser User user, @RequestBody TrainDataDTO trainDataDTO) {
        
        PageHelper.startPage(trainDataDTO.getPageNum(),trainDataDTO.getPageSize());
        //������ѯ
        QueryWrapper<TrainData> queryWrapper = new QueryWrapper();

        String dataCollectionName = trainDataDTO.getDataCollectionName();
        if(StringUtils.isNotBlank(dataCollectionName)){
            queryWrapper.lambda().eq(TrainData::getDataCollectionName, dataCollectionName);
        }

        //����
        // queryWrapper.lambda().orderByDesc(TrainData::getCreateTime);
        //ִ�в�ѯ
        //执行查询
        List<TrainDataVersionVo> list = trainDataService.listPage(user, trainDataDTO);
        PageInfo<TrainDataVersionVo> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody TrainDataDTO trainDataDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody TrainDataDTO trainDataDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        TrainData temp = trainDataService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        trainDataService.removeById(id);
        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ���ݶ��idɾ��
     **/
    @PostMapping("deleteByIds")
    public Result<?> deleteByIds(@Param("ids") String ids) throws Exception {
        //ҵ����
        if (StringUtils.isEmpty(ids)) {
            return Result.error(ErrorCodeEnum.PARAM_ERROR);
        }
        trainDataService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}


