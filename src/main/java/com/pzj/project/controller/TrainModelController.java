package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.TrainModel;
import com.pzj.project.dto.TrainModelDTO;
import com.pzj.project.service.TrainModelService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TrainModel)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@RestController
@RequestMapping("/trainModel")
public class TrainModelController {
    /**
     * �������
     */
    @Autowired
    private TrainModelService trainModelService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody TrainModelDTO trainModelDTO) {
        //������ѯ
        QueryWrapper<TrainModel> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(TrainModel::getCreateTime);
        //ִ�в�ѯ
        List<TrainModel> list = trainModelService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody TrainModelDTO trainModelDTO) {
        
        PageHelper.startPage(trainModelDTO.getPageNum(),trainModelDTO.getPageSize());
        //������ѯ
        QueryWrapper<TrainModel> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(TrainModel::getCreateTime);
        //ִ�в�ѯ
        List<TrainModel> list = trainModelService.list(queryWrapper);
        PageInfo<TrainModel> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody TrainModelDTO trainModelDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody TrainModelDTO trainModelDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        TrainModel temp = trainModelService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        trainModelService.removeById(id);
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
        trainModelService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}


