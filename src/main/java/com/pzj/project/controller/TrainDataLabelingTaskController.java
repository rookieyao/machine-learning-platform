package com.pzj.project.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzj.project.common.Result;
import com.pzj.project.entity.TrainDataLabelingTask;
import com.pzj.project.dto.TrainDataLabelingTaskDTO;
import com.pzj.project.service.TrainDataLabelingTaskService;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TrainDataLabelingTask)����Ʋ�
 *
 * @author makejava
 * @since 2023-02-18 20:41:33
 */
@RestController
@RequestMapping("/trainDataLabelingTask")
public class TrainDataLabelingTaskController {
    /**
     * �������
     */
    @Autowired
    private TrainDataLabelingTaskService trainDataLabelingTaskService;

    /**
     * �б��ѯ����ҳ ��ѯȫ��
     **/
    @PostMapping("/listAll")
    public Result<?> listAll(@RequestBody TrainDataLabelingTaskDTO trainDataLabelingTaskDTO) {
        //������ѯ
        QueryWrapper<TrainDataLabelingTask> queryWrapper = new QueryWrapper();
        //����
        // queryWrapper.lambda().orderByDesc(TrainDataLabelingTask::getCreateTime);
        //ִ�в�ѯ
        List<TrainDataLabelingTask> list = trainDataLabelingTaskService.list(queryWrapper);
        //��װ��ѯ���
        return Result.success(list);
    }
    
    /**
     * �б��ѯ ��ҳ
     * ֻ�ܲ�ѯ�Լ���
     **/
    @PostMapping("/listPage")
    public Result<?> listPage(@RequestBody TrainDataLabelingTaskDTO trainDataLabelingTaskDTO) {
        
        PageHelper.startPage(trainDataLabelingTaskDTO.getPageNum(),trainDataLabelingTaskDTO.getPageSize());
        //������ѯ
        QueryWrapper<TrainDataLabelingTask> queryWrapper = new QueryWrapper();
        
        //����
        // queryWrapper.lambda().orderByDesc(TrainDataLabelingTask::getCreateTime);
        //ִ�в�ѯ
        List<TrainDataLabelingTask> list = trainDataLabelingTaskService.list(queryWrapper);
        PageInfo<TrainDataLabelingTask> pageInfo = new PageInfo<>(list);
        //��װ��ѯ���
        return Result.success(pageInfo);
    }
    
   /**
     * ����
     **/
    @PostMapping("/insert")
    public Result<?> insert(@RequestBody TrainDataLabelingTaskDTO trainDataLabelingTaskDTO) {

                        //��װ��ѯ���
        return Result.success();
    }

    /**
     * ����id����
     **/
    @PostMapping("updateById")
    public Result<?> updateById(@RequestBody TrainDataLabelingTaskDTO trainDataLabelingTaskDTO) throws Exception {
        //�ֶμ��
                        
               //��װ��ѯ���
        return Result.success();
    }


    /**
     * ����id��ѯ
     **/
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable("id") Long id) {
        TrainDataLabelingTask temp = trainDataLabelingTaskService.getById(id);
        //��װ��ѯ���
        return Result.success(temp);
    }

    /**
     * ����id����
     **/
    @PostMapping("deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Long id) {
        trainDataLabelingTaskService.removeById(id);
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
        trainDataLabelingTaskService.deleteByIds(ids);
        //��װ��ѯ���
        return Result.success();
    }
    
    
}


