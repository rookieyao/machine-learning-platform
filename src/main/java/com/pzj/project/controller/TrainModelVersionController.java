package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.TrainModelVersion;
import com.pzj.project.dto.TrainModelVersionDTO;
import com.pzj.project.service.TrainModelVersionService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TrainModelVersion)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@RestController
@RequestMapping("/trainModelVersion")
public class TrainModelVersionController {
    /**
     * �������
     */
    @Autowired
    private TrainModelVersionService trainModelVersionService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody TrainModelVersionDTO trainModelVersionDTO) {
        //������ѯ
        QueryWrapper<TrainModelVersion> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(TrainModelVersion::getCreateTime);
        //ִ�в�ѯ
        List<TrainModelVersion> list = trainModelVersionService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody TrainModelVersionDTO trainModelVersionDTO) {
        
        PageHelper.startPage(trainModelVersionDTO.getPageNum(),trainModelVersionDTO.getPageSize());
        //������ѯ
        QueryWrapper<TrainModelVersion> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(TrainModelVersion::getCreateTime);
        //ִ�в�ѯ
        List<TrainModelVersion> list = trainModelVersionService.list(queryWrapper);
        PageInfo<TrainModelVersion> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody TrainModelVersionDTO trainModelVersionDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody TrainModelVersionDTO trainModelVersionDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        TrainModelVersion temp = trainModelVersionService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        trainModelVersionService.removeById(id);
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
        trainModelVersionService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}


