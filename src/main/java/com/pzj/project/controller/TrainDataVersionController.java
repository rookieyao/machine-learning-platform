package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.TrainDataVersion;
import com.pzj.project.dto.TrainDataVersionDTO;
import com.pzj.project.service.TrainDataVersionService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TrainDataVersion)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-15 21:52:48
 */
@RestController
@RequestMapping("/trainDataVersion")
public class TrainDataVersionController {
    /**
     * �������
     */
    @Autowired
    private TrainDataVersionService trainDataVersionService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody TrainDataVersionDTO trainDataVersionDTO) {
        //������ѯ
        QueryWrapper<TrainDataVersion> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(TrainDataVersion::getCreateTime);
        //ִ�в�ѯ
        List<TrainDataVersion> list = trainDataVersionService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody TrainDataVersionDTO trainDataVersionDTO) {
        
        PageHelper.startPage(trainDataVersionDTO.getPageNum(),trainDataVersionDTO.getPageSize());
        //������ѯ
        QueryWrapper<TrainDataVersion> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(TrainDataVersion::getCreateTime);
        //ִ�в�ѯ
        List<TrainDataVersion> list = trainDataVersionService.list(queryWrapper);
        PageInfo<TrainDataVersion> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody TrainDataVersionDTO trainDataVersionDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody TrainDataVersionDTO trainDataVersionDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        TrainDataVersion temp = trainDataVersionService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        trainDataVersionService.removeById(id);
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
        trainDataVersionService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}


